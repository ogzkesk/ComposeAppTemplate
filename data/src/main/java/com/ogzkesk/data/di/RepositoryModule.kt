package com.ogzkesk.data.di

import com.ogzkesk.data.repository.ad.AdRepositoryImpl
import com.ogzkesk.data.repository.store.DataStoreRepositoryImpl
import com.ogzkesk.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindDataStoreRepository(
        dataStoreImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    fun bindAdRepository(
        adRepositoryImpl: AdRepositoryImpl
    ) : AdRepository
}