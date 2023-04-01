package com.ogzkesk.core.ext

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ShareCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.ogzkesk.core.util.Constants

fun Context.toAction(uri: Uri) {
    startActivity(Intent(Intent.ACTION_VIEW, uri))
}

fun Context.toEmail(){
    ShareCompat.IntentBuilder(this)
        .setType("text/email")
        .setEmailTo(arrayOf("email@email.com"))
        .startChooser()
}

fun Context.showToast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.PREF_NAME)

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}
