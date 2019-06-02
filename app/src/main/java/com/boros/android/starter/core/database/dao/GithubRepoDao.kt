package com.boros.android.starter.core.database.dao

import androidx.room.*
import com.boros.android.starter.core.model.GithubRepo

@Dao
interface GithubRepoDao {

    @Query("select * from GithubRepo")
    fun getGithubRepos(): List<GithubRepo>

    @Query("select * from GithubRepo where id=:id LIMIT 1")
    fun getGithubRepo(id: Int): GithubRepo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubRepo(githubRepo: GithubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGithubRepos(githubRepos: List<GithubRepo>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGithubRepo(githubRepo: GithubRepo)

    @Query("DELETE FROM GithubRepo where id=:id")
    fun deleteGithubRepo(id: String)

    @Delete
    fun deleteGithubRepo(user: GithubRepo)

    @Query("DELETE FROM GithubRepo")
    fun deleteAll()

}