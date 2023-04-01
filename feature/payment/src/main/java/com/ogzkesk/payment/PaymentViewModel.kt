package com.ogzkesk.payment

import androidx.lifecycle.ViewModel
import com.ogzkesk.core.payment.PaymentHelper
import com.ogzkesk.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentHelper: PaymentHelper
): ViewModel() {


    fun onRestoreSubscription(){
        paymentHelper.restoreSubscription()
    }
}