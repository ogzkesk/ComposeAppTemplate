package com.ogzkesk.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

        @Provides
        @MainDispatcher
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @Provides
        @MainDispatcher.Immediate
        fun provideMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

        @Provides
        @DefaultDispatcher
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @IODispatcher
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @ApplicationScope
        @Provides
        @Singleton
        fun provideApplicationScope(): CoroutineScope = CoroutineScope(SupervisorJob())

}