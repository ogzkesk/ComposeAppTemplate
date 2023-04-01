package com.ogzkesk.core.ext


fun String.replaceIfContains(regex: String): String {
    return if (contains(regex)) {
        this.replace("$regex ", "")
    } else {
        this
    }
}

suspend fun String.doIfNotEmpty(action: suspend (String) -> Unit) {
    if (this.isNotEmpty()) {
        action(this)
    }
}