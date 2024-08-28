package com.android.boilerplate.features.feedback.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.feedback.domain.model.Feedback
import com.android.boilerplate.features.feedback.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 11/06/2024
 */
class GetFeedbackItemsUseCase @Inject constructor(private val repository: FeedbackRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<Feedback>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<Feedback>>> {
        return repository.getFeedbackItems()
    }
}