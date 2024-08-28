package com.android.boilerplate.features.userdetails.di

import com.android.boilerplate.features.userdetails.domain.repository.UserDetailsRepository
import com.android.boilerplate.features.userdetails.domain.repository.UserDetailsRepositoryImpl
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
abstract class UserDetailsModule {
    @Singleton
    @Binds
    abstract fun bindUserDetailsRepository(repo: UserDetailsRepositoryImpl): UserDetailsRepository
}