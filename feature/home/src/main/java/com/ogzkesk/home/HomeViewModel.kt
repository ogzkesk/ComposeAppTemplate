package com.ogzkesk.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogzkesk.core.di.IODispatcher
import com.ogzkesk.domain.use_cases.UseCases
import com.ogzkesk.domain.util.ThemePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _event = Channel<Event>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    val readThemePref: StateFlow<ThemePref> = useCases.appStore.readThemePref
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = ThemePref(darkMode = true, systemDef = false)
        )

    private val readIsAppStarted: StateFlow<Boolean> = useCases.appStore.readIsStarted
        .flowOn(ioDispatcher)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = true
        )

    private fun setPayWall() {
        println("readIsAppStarted :: ${readIsAppStarted.value}")
        val appState = readIsAppStarted.value
        viewModelScope.launch(ioDispatcher) {
            if(!appState){
                println("if Girdi appState :: false, event Send --> showPaywall")
                _event.send(Event.ShowPayWall)
                useCases.appStore.writeIsAppStarted(true)
                println("if Girdi appState :: false, ---> writedAppState true")
            }
        }
    }

    fun onThemeChange(isDarkModeOn: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            useCases.appStore.writeThemePref(
                ThemePref(darkMode = isDarkModeOn, systemDef = false)
            )
        }
    }

    sealed class Event{
        object ShowPayWall : Event()
    }

    init {
        setPayWall()
    }
}