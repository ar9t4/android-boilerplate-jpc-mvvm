package com.android.boilerplate.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.boilerplate.core.data.local.database.daos.RandomUserDao
import com.android.boilerplate.core.data.local.database.entities.RandomUser

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Database(entities = [RandomUser::class], exportSchema = false, version = 1)
abstract class Database : RoomDatabase() {
    abstract fun randomUserDao(): RandomUserDao
}