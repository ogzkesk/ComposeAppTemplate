package com.ogzkesk.composeapptemplate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.MobileAds
import com.ogzkesk.core.ui.theme.ComposeAppTemplateTheme
import com.ogzkesk.composeapptemplate.util.LocaleUtils
import com.ogzkesk.core.util.setThemePref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this) {}
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        setContent {
            val pref by mainViewModel.themePref.collectAsState()
            val lang by mainViewModel.languagePref.collectAsState()

            setLanguage(lang = lang)
            val isDarkModeOn = setThemePref(themePref = pref)

            ComposeAppTemplateTheme(isDarkModeOn) {
                Root(isDarkModeOn = isDarkModeOn)
            }
        }
    }

    private fun setLanguage(@StringRes lang: Int){
        LocaleUtils.setLocale(this, language = lang)
    }

    override fun onResume() {
        mainViewModel.checkSubscription()
        super.onResume()
    }
}