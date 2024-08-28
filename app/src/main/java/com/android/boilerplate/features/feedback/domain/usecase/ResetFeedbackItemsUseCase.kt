package com.android.boilerplate.features.feedback.domain.usecase

import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.feedback.domain.repository.FeedbackRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/06/2024
 */
class ResetFeedbackItemsUseCase @Inject constructor(private val repository: FeedbackRepository) :
    UseCase<NoParams, Unit> {

    override fun invoke(params: NoParams): Result<Unit> {
        return try {
            repository.resetFeedbackItems()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}