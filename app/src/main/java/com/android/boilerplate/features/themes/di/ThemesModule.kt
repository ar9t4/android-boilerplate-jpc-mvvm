package com.android.boilerplate.features.themes.di

import com.android.boilerplate.features.themes.domain.repository.ThemesRepository
import com.android.boilerplate.features.themes.domain.repository.ThemesRepositoryImpl
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
abstract class ThemesModule {
    @Singleton
    @Binds
    abstract fun bindThemesRepository(repo: ThemesRepositoryImpl): ThemesRepository
}