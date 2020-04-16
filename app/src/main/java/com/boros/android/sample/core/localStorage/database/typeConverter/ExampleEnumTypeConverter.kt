package com.boros.android.sample.core.localStorage.database.typeConverter

import androidx.room.TypeConverter
import com.boros.android.sample.core.model.enums.ExampleEnum

class ExampleEnumTypeConverter {

    @TypeConverter
    fun enumToString(enum: ExampleEnum): String = enum.toString()

    @TypeConverter
    fun stringToEnum(enum: String): ExampleEnum? = ExampleEnum.valueOf(enum)

}