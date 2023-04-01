package com.ogzkesk.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogzkesk.core.di.IODispatcher
import com.ogzkesk.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val isAppStarted: StateFlow<Boolean> = useCases.appStore.readIsStarted
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

}