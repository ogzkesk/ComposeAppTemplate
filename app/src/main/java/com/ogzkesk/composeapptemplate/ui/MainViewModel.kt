package com.ogzkesk.composeapptemplate.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogzkesk.core.R
import com.ogzkesk.core.di.IODispatcher
import com.ogzkesk.core.payment.PaymentHelper
import com.ogzkesk.domain.use_cases.UseCases
import com.ogzkesk.domain.util.ThemePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: UseCases,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val paymentHelper: PaymentHelper
) : ViewModel() {

    val themePref: StateFlow<ThemePref> = useCases.appStore.readThemePref
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ThemePref(
                darkMode = true,
                systemDef = false
            )
        )

    val languagePref: StateFlow<Int> = useCases.appStore.readLanguagePref
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = R.string.system_default
        )

    fun checkSubscription() = viewModelScope.launch {
        paymentHelper.checkSubscription()
    }

    init {
        checkSubscription()
    }
}