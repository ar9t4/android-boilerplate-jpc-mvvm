package com.android.boilerplate.features.more.di

import com.android.boilerplate.features.more.domain.repository.MoreRepository
import com.android.boilerplate.features.more.domain.repository.MoreRepositoryImpl
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
abstract class MoreModule {
    @Singleton
    @Binds
    abstract fun bindMoreRepository(repo: MoreRepositoryImpl): MoreRepository
}