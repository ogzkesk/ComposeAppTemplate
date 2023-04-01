package com.ogzkesk.domain.use_cases

import android.app.Activity
import com.ogzkesk.domain.repository.AdRepository
import javax.inject.Inject

class ShowAds @Inject constructor(private val adRepository: AdRepository) {

    suspend operator fun invoke(activity: Activity) = adRepository.showAd(activity)
}