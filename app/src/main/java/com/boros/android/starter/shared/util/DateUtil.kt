package com.boros.android.starter.shared.util

import android.content.Context
import android.os.Build
import java.util.*

// TODO refactor
object DateUtil {

    val standardFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    val completeDateFormat = "yyyy-MM-dd HH:mm"
    val yearShortMonthShortDayFormat = "yyyy.MM.dd."
    val yearMonthShortDayFormat = "yyyy. MMMM d."
    val detailedMonthDayFormat = "MMMM dd. EEEE"
    val dayFormat = "EEEE"
    val hourMinuteFormat = "HH:mm"
    val monthFormat = "MMM"
    val simpleDayFormat = "d"

    fun getCurrentDate(): Date {
        return Date()
    }

    fun getLocaleFromContext(context: Context?): Locale {
        val configuration = context?.resources?.configuration
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration?.locales?.get(0) ?: Locale.getDefault()
        } else {
            configuration?.locale ?: Locale.getDefault()
        }
    }

}