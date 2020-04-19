package com.boros.android.sample.shared.util.extensions

import java.text.NumberFormat
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

fun Double?.formatAccordingLocale(locale: Locale): String {
    if (this != null) {
        val numberFormat = NumberFormat.getInstance(locale)
        return numberFormat.format(Math.round(this))
    }
    return ""
}

fun Double.preciseRound(): Double {
    val scale = 10.0.pow(2.0)
    return (this * scale).roundToInt().toDouble() / scale
}

fun Double?.formatToString(): String {
    return if (this == this?.toLong()?.toDouble())
        String.format("%d", this?.toLong())
    else
        String.format("%s", this)
}