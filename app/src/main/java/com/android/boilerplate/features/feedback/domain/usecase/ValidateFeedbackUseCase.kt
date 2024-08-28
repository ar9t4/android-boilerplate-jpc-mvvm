package com.android.boilerplate.features.feedback.domain.usecase

import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.feedback.domain.model.Feedback
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/06/2024
 */
class ValidateFeedbackUseCase @Inject constructor() :
    UseCase<Pair<List<Feedback>, String>, Boolean> {

    override fun invoke(params: Pair<List<Feedback>, String>): Result<Boolean> {
        return try {
            val items = params.first
            val feedback = params.second
            val isAnySelected = items.any { it.selected }
            if (!isAnySelected && feedback.trim().isEmpty()) {
                Result.success(false)
            } else {
                Result.success(true)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}