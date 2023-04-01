package com.ogzkesk.data.repository.ad

import android.content.Context
import androidx.activity.ComponentActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.ogzkesk.data.util.Constants.REWARDED_AD_ID
import com.ogzkesk.domain.repository.AdRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import javax.inject.Inject

class AdRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AdRepository {

    private var rewardedAd : RewardedAd? = null
    private val adRequest = AdRequest.Builder().build()


    override suspend fun showAd(activity: Any) : Int? {
        var result : Int? = null

        RewardedAd.load(context, REWARDED_AD_ID, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                rewardedAd = ad
            }
        })

        while (result == null){
            rewardedAd?.let { ad ->
                ad.show(activity as ComponentActivity, OnUserEarnedRewardListener {
                    result = it.amount
                })
            }
            delay(1000)
        }

        return result
    }
}