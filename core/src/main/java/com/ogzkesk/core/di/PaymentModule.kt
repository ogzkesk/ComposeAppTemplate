package com.ogzkesk.core.di

import android.content.Context
import com.ogzkesk.core.payment.PaymentHelper
import com.ogzkesk.domain.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentModule {


        @Provides
        @Singleton
        fun providePaymentHelper(
            useCases: UseCases,
            @ApplicationContext context: Context,
            @ApplicationScope scope: CoroutineScope,
        ) : PaymentHelper =
            PaymentHelper(
                context = context,
                useCases = useCases,
                scope = scope,
            )

}