package com.ogzkesk.data.di

import android.content.Context
import androidx.room.Room
import com.ogzkesk.data.local.AppDao
import com.ogzkesk.data.local.AppDatabase
import com.ogzkesk.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.APP_DB)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAppDao(appDatabase: AppDatabase) : AppDao =
        appDatabase.appDao()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()


}