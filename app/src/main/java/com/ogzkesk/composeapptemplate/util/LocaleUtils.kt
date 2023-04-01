package com.ogzkesk.composeapptemplate.util

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import com.ogzkesk.core.R
import java.util.*

object LocaleUtils {

    fun setLocale(c: Context, language: Int) = updateResources(c, language)

    private fun updateResources(context: Context, language: Int) {
        context.resources.apply {
            when(language){
                R.string.system_default -> {
                    val locals = Resources.getSystem().configuration.locales
                    val locale = Locale(locals[0].language)
                    val config = Configuration(configuration)

                    context.createConfigurationContext(configuration)
                    Locale.setDefault(locale)
                    config.setLocale(locale)
                    context.resources.updateConfiguration(config, displayMetrics)
                }
                R.string.english -> {
                    val locale = Locale(Locale.ENGLISH.language)
                    val config = Configuration(configuration)

                    context.createConfigurationContext(configuration)
                    Locale.setDefault(locale)
                    config.setLocale(locale)
                    context.resources.updateConfiguration(config, displayMetrics)
                }
            }
        }
    }
}