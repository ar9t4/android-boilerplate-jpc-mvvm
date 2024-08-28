package com.android.boilerplate.features.users.di

import com.android.boilerplate.features.users.domain.repository.UsersRepository
import com.android.boilerplate.features.users.domain.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Abdul Rahman on 14/05/2024
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {
    @Singleton
    @Binds
    abstract fun bindUsersRepository(repo: UsersRepositoryImpl): UsersRepository
}