package com.boros.android.starter.core.localStorage.database.dao

import androidx.room.*
import com.boros.android.starter.core.model.GithubRepo

@Dao
interface GithubRepoDao {

    @Query("select * from GithubRepo")
    suspend fun getGithubRepos(): List<GithubRepo>?

    @Query("select * from GithubRepo where id=:id LIMIT 1")
    suspend fun getGithubRepo(id: Int): GithubRepo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepo(githubRepo: GithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepos(githubRepos: List<GithubRepo>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGithubRepo(githubRepo: GithubRepo)

    @Query("DELETE FROM GithubRepo where id=:id")
    suspend fun deleteGithubRepo(id: String)

    @Delete
    suspend fun deleteGithubRepo(user: GithubRepo)

    @Query("DELETE FROM GithubRepo")
    suspend fun deleteAll()

}