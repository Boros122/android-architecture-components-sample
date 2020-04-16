package com.boros.android.sample.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorMessage(val message: String) : Parcelable