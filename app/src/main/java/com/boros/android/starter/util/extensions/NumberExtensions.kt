package com.boros.android.starter.util.extensions

import java.text.NumberFormat
import java.util.*

fun Double?.formatAccordingLocale(locale: Locale): String {
    if (this != null) {
        val numberFormat = NumberFormat.getInstance(locale)
        return numberFormat.format(Math.round(this))
    }
    return ""
}

fun Double.precizeRound(): Double {
    val scale = Math.pow(10.0, 2.0)
    return Math.round(this * scale).toDouble() / scale
}

fun Double?.formatToString(): String {
    return if (this == this?.toLong()?.toDouble())
        String.format("%d", this?.toLong())
    else
        String.format("%s", this)
}