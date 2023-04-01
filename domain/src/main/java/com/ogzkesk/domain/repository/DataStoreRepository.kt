package com.ogzkesk.domain.repository

import com.ogzkesk.domain.util.ThemePref
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun writeIsStarted(isOn : Boolean)
    val readIsStarted : Flow<Boolean>

    suspend fun writeIsRateUsShow(isShowed: Boolean)
    val readIsRateUsShowed : Flow<Boolean>

    suspend fun writeIsUserPro(pro: Boolean)
    val readIsUserPro : Flow<Boolean>

    suspend fun writeThemePref(pref: ThemePref)
    val readThemePref : Flow<ThemePref>

    suspend fun writeLanguagePref(language: Int)
    val readLanguagePref : Flow<Int>

}