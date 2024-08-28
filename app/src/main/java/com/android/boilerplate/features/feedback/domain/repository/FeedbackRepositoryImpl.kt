package com.android.boilerplate.features.feedback.domain.repository

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.feedback.domain.model.Feedback
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class FeedbackRepositoryImpl @Inject constructor(
    private val activityContextProvider: ActivityContextProvider
) : FeedbackRepository {

    private val feedbackItems = mutableListOf<Feedback>()
    private val context: Context get() = activityContextProvider.getActivityContext()

    override suspend fun getFeedbackItems(): Flow<Resource<List<Feedback>>> {
        return flow {
            if (feedbackItems.isEmpty()) {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
            }
            context.apply {
                val items = listOf(
                    Feedback(
                        id = 0,
                        name = getString(R.string.improving_design),
                        getStateOfFeedbackItem(index = 0)
                    ),
                    Feedback(
                        id = 1,
                        name = getString(R.string.improving_experience),
                        getStateOfFeedbackItem(index = 1)
                    ),
                    Feedback(
                        id = 2,
                        name = getString(R.string.improving_functionality),
                        getStateOfFeedbackItem(index = 2)
                    ),
                    Feedback(
                        id = 3,
                        name = getString(R.string.improving_performance),
                        getStateOfFeedbackItem(index = 3)
                    )
                )
                feedbackItems.clear()
                feedbackItems.addAll(items)
                emit(value = Resource.Success(data = items))
            }
        }
    }

    private fun getStateOfFeedbackItem(index: Int): Boolean {
        if (feedbackItems.isNotEmpty()) {
            return feedbackItems[index].selected
        }
        return false
    }

    override fun selectDeselectFeedbackItem(id: Int) {
        val index = feedbackItems.indexOfFirst { it.id == id }
        if (index != -1) {
            val feedbackItem = feedbackItems[index]
            feedbackItems[index] = feedbackItem.copy(selected = !feedbackItem.selected)
        }
    }

    override fun resetFeedbackItems() {
        feedbackItems.forEachIndexed { index, feedback ->
            feedbackItems[index] = feedback.copy(selected = false)
        }
    }
}