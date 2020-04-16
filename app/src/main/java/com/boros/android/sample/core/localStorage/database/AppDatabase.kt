package com.boros.android.sample.core.localStorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boros.android.sample.core.localStorage.database.dao.GithubRepoDao
import com.boros.android.sample.core.localStorage.database.typeConverter.DateTypeConverter
import com.boros.android.sample.core.localStorage.database.typeConverter.ExampleEnumTypeConverter
import com.boros.android.sample.core.localStorage.database.typeConverter.ListTypeConverter
import com.boros.android.sample.core.localStorage.database.typeConverter.StringTypeConverter
import com.boros.android.sample.core.model.GithubRepo

@Database(entities = [
    (GithubRepo::class)
], version = 1, exportSchema = false)

@TypeConverters(
        DateTypeConverter::class,
        ListTypeConverter::class,
        StringTypeConverter::class,
        ExampleEnumTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}