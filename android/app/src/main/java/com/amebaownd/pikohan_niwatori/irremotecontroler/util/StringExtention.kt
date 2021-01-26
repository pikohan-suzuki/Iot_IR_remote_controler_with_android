package com.amebaownd.pikohan_niwatori.irremotecontroler.util

fun String?.isNotNullOrBlankOrEmpty(): Boolean {
    if (this != null)
        return this.isNotEmpty() && this.isNotBlank()
    return false
}