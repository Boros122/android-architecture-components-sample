package com.boros.android.sample.core.localStorage.database.typeConverter

import androidx.room.TypeConverter
import java.util.*

class StringTypeConverter {

    private val separator = " ,"

    @TypeConverter
    fun stringArrayToString(strings: ArrayList<String>?): String? =
            strings?.joinToString(separator = separator) { it }

    @TypeConverter
    fun stringToStringArray(string: String?): ArrayList<String>? {
        return string?.split(separator)?.map { it } as ArrayList<String>
    }

}