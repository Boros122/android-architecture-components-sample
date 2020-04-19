package com.boros.android.sample.shared.util.extensions

import android.content.res.Resources
import com.boros.android.sample.R

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