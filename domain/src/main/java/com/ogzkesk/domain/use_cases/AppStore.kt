package com.ogzkesk.domain.use_cases

import com.ogzkesk.domain.repository.DataStoreRepository
import com.ogzkesk.domain.util.ThemePref
import javax.inject.Inject

class AppStore @Inject constructor(private val store: DataStoreRepository) {

    suspend fun writeIsAppStarted(isOn : Boolean) = store.writeIsStarted(isOn)
    val readIsStarted = store.readIsStarted

    suspend fun writeIsUserPro(isPro : Boolean) = store.writeIsUserPro(isPro)
    val readIsUserPro = store.readIsUserPro

    suspend fun writeThemePref(pref: ThemePref) = store.writeThemePref(pref)
    val readThemePref = store.readThemePref

    suspend fun writeLanguagePref(language: Int) = store.writeLanguagePref(language)
    val readLanguagePref = store.readLanguagePref

    suspend fun writeIsRateUsShowed(isShowed: Boolean) = store.writeIsRateUsShow(isShowed)
    val readIsRateUsShowed = store.readIsRateUsShowed


}