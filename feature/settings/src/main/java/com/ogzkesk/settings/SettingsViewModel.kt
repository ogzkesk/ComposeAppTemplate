package com.ogzkesk.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogzkesk.core.di.IODispatcher
import com.ogzkesk.domain.use_cases.UseCases
import com.ogzkesk.domain.util.ThemePref
import com.ogzkesk.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val useCases: UseCases,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    val themePref : StateFlow<ThemePref> = useCases.appStore.readThemePref
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ThemePref(darkMode = true,systemDef = false)
        )

    val languagePref : StateFlow<Int> = useCases.appStore.readLanguagePref
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = R.string.system_default
        )

    fun onThemePrefChange(titleRes : Int) = viewModelScope.launch(ioDispatcher) {
        val themePref = when(titleRes){
            R.string.dark_mode -> ThemePref(darkMode = true, systemDef = false)
            R.string.light_mode -> ThemePref(darkMode = false, systemDef = false)
            R.string.system_default -> ThemePref(darkMode = false, systemDef = true)
            else -> ThemePref(darkMode = true,systemDef = false)
        }
        useCases.appStore.writeThemePref(themePref)
    }

    fun onLanguagePrefChange(language: Int) = viewModelScope.launch(ioDispatcher) {
        useCases.appStore.writeLanguagePref(language)
    }


}