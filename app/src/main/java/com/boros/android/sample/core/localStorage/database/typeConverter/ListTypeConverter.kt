package com.boros.android.sample.core.localStorage.database.typeConverter

import androidx.room.TypeConverter
import com.boros.android.sample.core.model.GithubRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListTypeConverter {

    private val jsonConverter = Gson()

    @TypeConverter
    fun listToString(list: ArrayList<GithubRepo>?): String? {
        if (list == null) {
            return null
        }
        return jsonConverter.toJson(list)
    }

    @TypeConverter
    fun stringToList(data: String?): ArrayList<GithubRepo>? {
        if (data == null) {
            return null
        }
        val type = object : TypeToken<ArrayList<GithubRepo>>() {}.type
        return jsonConverter.fromJson(data, type)
    }

}