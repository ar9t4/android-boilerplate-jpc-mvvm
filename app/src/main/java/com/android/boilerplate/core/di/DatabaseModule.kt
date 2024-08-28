package com.android.boilerplate.core.di

import android.content.Context
import androidx.room.Room
import com.android.boilerplate.R
import com.android.boilerplate.core.data.local.database.Database
import com.android.boilerplate.core.data.local.database.daos.RandomUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room
            .databaseBuilder(context, Database::class.java, context.getString(R.string.app_name))
            .build()
    }

    @Provides
    fun provideRandomUserDao(database: Database): RandomUserDao {
        return database.randomUserDao()
    }
}