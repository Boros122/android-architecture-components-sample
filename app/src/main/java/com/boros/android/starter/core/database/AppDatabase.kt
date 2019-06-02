package com.boros.android.starter.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.boros.android.starter.core.database.dao.GithubRepoDao
import com.boros.android.starter.core.database.typeConverter.DateTypeConverter
import com.boros.android.starter.core.database.typeConverter.ExampleEnumTypeConverter
import com.boros.android.starter.core.database.typeConverter.ListTypeConverter
import com.boros.android.starter.core.database.typeConverter.StringTypeConverter
import com.boros.android.starter.core.model.GithubRepo

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