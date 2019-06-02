package com.boros.android.starter.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorMessage(val message: String) : Parcelable