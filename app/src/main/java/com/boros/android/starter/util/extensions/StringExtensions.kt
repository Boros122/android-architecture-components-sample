package com.boros.android.starter.util.extensions

import android.content.Context
import android.content.res.Resources
import com.boros.android.starter.R
import com.boros.android.starter.util.manager.DialogManager
import java.text.SimpleDateFormat
import java.util.*

fun String?.isEmail(resources: Resources): Boolean {
    return this != null && this.matches(Regex(resources.getString(R.string.email_regexp)))
}

fun String?.isPhoneNumber(resources: Resources): Boolean {
    return this != null && this.matches(Regex(resources.getString(R.string.hun_phone_number_regexp)))
}

fun String?.isValidPassword(resources: Resources): Boolean {
    return this != null && this.matches(Regex(resources.getString(R.string.password_regexp)))
}

fun String?.isValidTaxNumber(resources: Resources): Boolean {
    return this != null && this.matches(Regex(resources.getString(R.string.hun_tax_number_regexp)))
}

fun String?.toDate(locale: Locale = Locale.getDefault()): Date = SimpleDateFormat("yyyy-MM-dd", locale).parse(this)

fun String?.showAsDialog(context: Context?, positiveAction: () -> Unit = {}) {
    if (!this.isNullOrEmpty()) DialogManager.showInfoDialog(context, this, positiveAction)
}