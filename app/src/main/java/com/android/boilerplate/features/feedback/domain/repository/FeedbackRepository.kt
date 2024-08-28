package com.android.boilerplate.features.feedback.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.feedback.domain.model.Feedback
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface FeedbackRepository {
    suspend fun getFeedbackItems(): Flow<Resource<List<Feedback>>>
    fun selectDeselectFeedbackItem(id: Int)
    fun resetFeedbackItems()
}