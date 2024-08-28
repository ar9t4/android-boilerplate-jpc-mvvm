package com.android.boilerplate.features.languages.di

import com.android.boilerplate.features.languages.domain.repository.LanguagesRepository
import com.android.boilerplate.features.languages.domain.repository.LanguagesRepositoryImpl
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
abstract class LanguagesModule {
    @Singleton
    @Binds
    abstract fun bindLanguagesRepository(repo: LanguagesRepositoryImpl): LanguagesRepository
}