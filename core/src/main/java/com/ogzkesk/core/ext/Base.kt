package com.ogzkesk.core.ext

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult


fun BillingResult.checkResponse() : Boolean {
    return this.responseCode == BillingClient.BillingResponseCode.OK
}

