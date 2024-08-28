package com.android.boilerplate.features.feedback.di

import com.android.boilerplate.features.feedback.domain.repository.FeedbackRepository
import com.android.boilerplate.features.feedback.domain.repository.FeedbackRepositoryImpl
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
abstract class FeedbackModule {
    @Singleton
    @Binds
    abstract fun bindFeedbackRepository(repo: FeedbackRepositoryImpl): FeedbackRepository
}