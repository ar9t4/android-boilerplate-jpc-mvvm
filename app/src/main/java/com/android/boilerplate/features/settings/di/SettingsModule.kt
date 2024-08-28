package com.android.boilerplate.features.settings.di

import com.android.boilerplate.features.settings.domain.repository.SettingsRepository
import com.android.boilerplate.features.settings.domain.repository.SettingsRepositoryImpl
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
abstract class SettingsModule {
    @Singleton
    @Binds
    abstract fun bindSettingsRepository(repo: SettingsRepositoryImpl): SettingsRepository
}