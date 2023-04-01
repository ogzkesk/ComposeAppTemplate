package com.ogzkesk.core.payment

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingFlowParams.ProductDetailsParams
import com.google.common.collect.ImmutableList
import com.ogzkesk.core.R
import com.ogzkesk.core.di.ApplicationScope
import com.ogzkesk.core.ext.checkResponse
import com.ogzkesk.core.ext.showToast
import com.ogzkesk.domain.use_cases.UseCases
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class PaymentHelper @Inject constructor(
    private val useCases: UseCases,
    @ApplicationContext private val context: Context,
    @ApplicationScope private val scope: CoroutineScope,
) {

    private val TAG = "PaymentHelper"
    private val _productDetailsList = MutableStateFlow<List<ProductDetails>>(emptyList())
    val productDetailsList = _productDetailsList.asStateFlow()

    private lateinit var billingClient: BillingClient

    private fun init() {
        println("paymentHelper -- > init running")
        billingClient = BillingClient.newBuilder(context)
            .enablePendingPurchases()
            .setListener { result, purchases ->
                println("paymentHelper -- > init listener running")
                if (result.checkResponse() && purchases != null) {
                    purchases.forEach { _ ->
                        // success
                        verifyPurchase()
                    }
                }
            }
            .build()

        startConnection()
    }

    fun startConnection() {
        println("paymentHelper -- > startConnection running")
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                startConnection()
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                println("paymentHelper -- > startConnection listener setupFinished running")
                showProducts()
            }
        })
    }

    fun showProducts() {
        println("paymentHelper -- > showProducts running")
        val productList: ImmutableList<QueryProductDetailsParams.Product> = ImmutableList.of(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("Subscription 1 name")
                .setProductType(BillingClient.ProductType.SUBS)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("Subscription 2 name")
                .setProductType(BillingClient.ProductType.SUBS)
                .build(),
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId("Subscription 3 name")
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        )

        val params = QueryProductDetailsParams.newBuilder()
            .setProductList(productList)
            .build()

        billingClient.queryProductDetailsAsync(params) { _, prodDetailsList ->
            _productDetailsList.value = emptyList()
            Handler(Looper.getMainLooper()).postDelayed({
                println("paymentHelper -- > showProducts -> queryProductDetails running size :: ${prodDetailsList.size}")
                println("handler postDelayed")
                _productDetailsList.value = prodDetailsList
            }, 2000)
        }
    }

    fun launchPurchaseFlow(productDetails: ProductDetails, activity: ComponentActivity) {
        println("paymentHelper --> launchPurchaseFlow running")
        if (productDetails.subscriptionOfferDetails == null) return
        println("paymentHelper --> launchPurchaseFlow > subsList not null -> running")
        val productDetailsParamsList = ImmutableList.of(
            ProductDetailsParams.newBuilder()
                .setProductDetails(productDetails)
                .setOfferToken(productDetails.subscriptionOfferDetails!!.first().offerToken)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

        billingClient.launchBillingFlow(
            activity,
            billingFlowParams
        )
    }

    private fun verifyPurchase() {
        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build()
        ) { billingResult: BillingResult, list: List<Purchase> ->
            if (billingResult.checkResponse()) {
                for (purchase in list) {
                    if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
                        verifySubPurchase(purchase)
                    }
                }
            }
        }
    }

    private fun verifySubPurchase(purchases: Purchase) {
        println("paymentHelper --> veryifySubPurchase running")
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchases.purchaseToken)
            .build()

        billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult: BillingResult ->
            if (billingResult.checkResponse()) {
                println("paymentHelper --> verifySubPurchase --> acknowledge response : OK - > running")
                context.showToast(R.string.sub_activated)
                scope.launch {
                    println("paymentHelper --> scope.launch girdi")
                    useCases.appStore.writeIsUserPro(true)
                }
            }
        }

        Log.d(TAG, "Purchase Token: " + purchases.purchaseToken)
        Log.d(TAG, "Purchase Time: " + purchases.purchaseTime)
        Log.d(TAG, "Purchase OrderID: " + purchases.orderId)
    }


    fun checkSubscription() {
        println("paymentHelper --> checkSubscription running")
        val finalBillingClient = BillingClient.newBuilder(context)
            .enablePendingPurchases()
            .setListener { billingResult, purchases -> }
            .build()

        finalBillingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {}
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.checkResponse()) {
                    println("paymentHelper --> checkSubscription > listener response : OK -> running")
                    finalBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                            .setProductType(BillingClient.ProductType.SUBS)
                            .build()
                    ) { billingResult1: BillingResult, list: List<Purchase> ->
                        println("paymentHelper --> checkSubscription > queryPurchase running")
                        if (billingResult1.checkResponse()) {
                            println("paymentHelper --> checkSubscription > queryPurchase response : OK -> running")
                            if (list.isNotEmpty()) {
                                println("paymentHelper --> checkSubscription > queryPurchase list NOT EMPTY :: USER PRO = TRUE")
                                scope.launch { useCases.appStore.writeIsUserPro(true) }
                                for ((i, purchase) in list.withIndex()) {
                                    //Here you can manage each product, if you have multiple subscription
                                    Log.d("testOffer", purchase.originalJson)
                                    Log.d("testOffer", " index$i")
                                }
                            } else {
                                scope.launch {
                                    useCases.appStore.writeIsUserPro(false)
                                }
                            }
                        }
                    }
                }
            }
        })
    }

    fun restoreSubscription() {
        println("paymentHelper --> restoreSubscription running")
        val finalBillingClient = BillingClient.newBuilder(context)
            .enablePendingPurchases()
            .setListener { billingResult, purchases -> }
            .build()

        finalBillingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {}
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.checkResponse()) {
                    println("paymentHelper --> restoreSubscription > listener response : OK - > running")
                    finalBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                            .setProductType(BillingClient.ProductType.SUBS)
                            .build()
                    ) { billingResult1: BillingResult, list: List<Purchase> ->
                        if (billingResult1.checkResponse()) {
                            println("paymentHelper --> restoreSubscription > queryPurchase response : OK - > running")
                            Log.d("testOffer", list.size.toString() + " size")
                            if (list.isNotEmpty()) {
                                println("paymentHelper --> restoreSubscription > queryPurchase list is NOT EMPTY :: USER PRO = TRUE")
                                scope.launch { useCases.appStore.writeIsUserPro(true) }
                                for ((i, purchase) in list.withIndex()) {
                                    //Here you can manage each product, if you have multiple subscription
                                    Log.d("testOffer", purchase.originalJson)
                                    Log.d("testOffer", " index$i")
                                }
                            } else {
                                scope.launch {
                                    useCases.appStore.writeIsUserPro(false)
                                }
                            }
                        }
                    }
                }
            }
        })
    }


    init {
        init()
    }
}