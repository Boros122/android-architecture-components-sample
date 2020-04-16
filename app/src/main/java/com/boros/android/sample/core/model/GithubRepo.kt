package com.boros.android.sample.core.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class GithubRepo(
        @PrimaryKey
        var id: Int = 0,
        var name: String? = null,
        @SerializedName("private")
        var isPrivate: Boolean? = null,
        var url: String? = null,
        @Embedded(prefix = "owner_")
        var owner: Owner? = null,
        var description: String? = null
) : Parcelable