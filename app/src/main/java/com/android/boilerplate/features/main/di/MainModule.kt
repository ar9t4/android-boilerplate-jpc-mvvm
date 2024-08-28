package com.android.boilerplate.features.main.di

import com.android.boilerplate.features.main.domain.repository.MainRepository
import com.android.boilerplate.features.main.domain.repository.MainRepositoryImpl
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
abstract class MainModule {
    @Singleton
    @Binds
    abstract fun bindMainRepository(repo: MainRepositoryImpl): MainRepository
}