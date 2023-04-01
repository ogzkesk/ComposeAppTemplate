package com.ogzkesk.core.di

import javax.inject.Qualifier

@Qualifier
annotation class MainDispatcher {

    @Qualifier
    annotation class Immediate
}

@Qualifier
annotation class DefaultDispatcher

@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope