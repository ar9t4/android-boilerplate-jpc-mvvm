package com.android.boilerplate.features.feedback.domain.usecase

import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.feedback.domain.repository.FeedbackRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 12/06/2024
 */
class SelectDeselectFeedbackItemUseCase @Inject constructor(
    private val repository: FeedbackRepository
) : UseCase<Int, Unit> {

    override fun invoke(params: Int): Result<Unit> {
        return try {
            repository.selectDeselectFeedbackItem(id = params)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}