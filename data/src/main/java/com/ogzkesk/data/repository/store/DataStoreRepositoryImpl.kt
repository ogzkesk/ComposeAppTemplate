package com.ogzkesk.data.repository.store

import android.content.Context
import androidx.datastore.preferences.core.*
import com.ogzkesk.core.di.IODispatcher
import com.ogzkesk.core.ext.dataStore
import com.ogzkesk.core.R
import com.ogzkesk.data.util.Prefs.APP_STARTED
import com.ogzkesk.data.util.Prefs.DARK_MODE_PREF
import com.ogzkesk.data.util.Prefs.LANGUAGE_PREF
import com.ogzkesk.data.util.Prefs.RATE_US_PREF
import com.ogzkesk.data.util.Prefs.SYSTEM_THEME_PREF
import com.ogzkesk.data.util.Prefs.USER_PRO_STATUS
import com.ogzkesk.domain.repository.DataStoreRepository
import com.ogzkesk.domain.util.ThemePref
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    @IODispatcher ioDispatcher: CoroutineDispatcher
) : DataStoreRepository {

    private val dataStore = context.dataStore

    private object PreferencesKeys {
        val isAppStarted = booleanPreferencesKey(APP_STARTED)
        val userProStatus = booleanPreferencesKey(USER_PRO_STATUS)
        val darkModePref = booleanPreferencesKey(DARK_MODE_PREF)
        val systemThemePref = booleanPreferencesKey(SYSTEM_THEME_PREF)
        val languageKey = intPreferencesKey(LANGUAGE_PREF)
        val RateUsPref = booleanPreferencesKey(RATE_US_PREF)
    }


    override suspend fun writeIsStarted(isOn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.isAppStarted] = isOn
        }
    }

    override val readIsStarted: Flow<Boolean> = dataStore.data
        .catch { e -> if (e is IOException) emptyPreferences() else throw e }
        .map { preferences -> preferences[PreferencesKeys.isAppStarted] ?: false }
        .flowOn(ioDispatcher)


    override suspend fun writeIsUserPro(pro: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.userProStatus] = pro
        }
    }

    override val readIsUserPro: Flow<Boolean> = dataStore.data
        .catch { e -> if (e is IOException) emptyPreferences() else throw e }
        .map { preferences -> preferences[PreferencesKeys.userProStatus] ?: false }
        .flowOn(ioDispatcher)


    override suspend fun writeThemePref(pref: ThemePref) {
        dataStore.edit {preferences ->
            preferences[PreferencesKeys.darkModePref] = pref.darkMode
            preferences[PreferencesKeys.systemThemePref] = pref.systemDef
        }
    }

    override val readThemePref: Flow<ThemePref> = dataStore.data
        .catch { e -> if (e is IOException) emptyPreferences() else throw e }
        .map { preferences ->
            val systemDefault = preferences[PreferencesKeys.systemThemePref] ?: false
            val darkMode = preferences[PreferencesKeys.darkModePref] ?: true
            ThemePref(darkMode = darkMode, systemDef = systemDefault)
        }
        .flowOn(ioDispatcher)


    override suspend fun writeLanguagePref(language: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.languageKey] = language
        }
    }

    override val readLanguagePref: Flow<Int> = dataStore.data
        .catch { e -> if(e is IOException) emptyPreferences() else throw e }
        .map { preferences -> preferences[PreferencesKeys.languageKey] ?: R.string.system_default }
        .flowOn(ioDispatcher)


    override suspend fun writeIsRateUsShow(isShowed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.RateUsPref] = isShowed
        }
    }

    override val readIsRateUsShowed: Flow<Boolean> = dataStore.data
        .catch { e -> if(e is IOException) emptyPreferences() else throw e }
        .map { preferences -> preferences[PreferencesKeys.RateUsPref] ?: false }
        .flowOn(ioDispatcher)

}