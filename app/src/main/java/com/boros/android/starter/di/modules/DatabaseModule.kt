package com.boros.android.starter.di.modules

import android.content.Context
import androidx.room.Room
import com.boros.android.starter.BuildConfig
import com.boros.android.starter.core.localStorage.database.AppDatabase
import com.boros.android.starter.core.localStorage.database.dao.GithubRepoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): AppDatabase {
        val databaseName = BuildConfig.DATABASE_NAME
        return Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
    }

    @Singleton
    @Provides
    fun provideGithubRepoDao(database: AppDatabase): GithubRepoDao {
        return database.githubRepoDao()
    }

}