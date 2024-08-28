package com.android.boilerplate.core.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Dao
interface RandomUserDao {
    @Query("select * from randomuser")
    suspend fun getUsers(): List<RandomUser>

    @Query("select * from randomuser where id = :id")
    fun getUser(id: Int): RandomUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<RandomUser>)

    @Query("delete from randomuser")
    suspend fun deleteAllUsers()
}