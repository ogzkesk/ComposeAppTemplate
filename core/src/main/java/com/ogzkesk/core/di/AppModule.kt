package com.ogzkesk.core.di

import com.ogzkesk.domain.repository.AdRepository
import com.ogzkesk.domain.repository.DataStoreRepository
import com.ogzkesk.domain.use_cases.AppStore
import com.ogzkesk.domain.use_cases.ShowAds
import com.ogzkesk.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUseCases(
        dataStoreRepository: DataStoreRepository,
        adRepository: AdRepository
    ) : UseCases =
        UseCases(
            appStore = AppStore(dataStoreRepository),
            showAds = ShowAds(adRepository)
        )

//    @Provides
//    @Singleton
//    fun provideRemoteConfig(): FirebaseRemoteConfig {
//        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
//        val configSettings = remoteConfigSettings {
//            minimumFetchIntervalInSeconds = 3600
//        }
//
//        remoteConfig.setConfigSettingsAsync(configSettings)
//        val mPrefs: MutableMap<String, Any> = HashMap()
//        mPrefs[CONFIG_FIELD_1] = false
//        mPrefs[CONFIG_FIELD_2] = ""
//
//        remoteConfig.setDefaultsAsync(mPrefs)
//        return remoteConfig
//    }


}