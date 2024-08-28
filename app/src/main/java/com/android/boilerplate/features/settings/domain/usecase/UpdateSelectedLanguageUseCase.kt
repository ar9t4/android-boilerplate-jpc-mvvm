package com.android.boilerplate.features.settings.domain.usecase

import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.settings.domain.repository.SettingsRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 15/07/2024
 */
class UpdateSelectedLanguageUseCase @Inject constructor(
    private val repository: SettingsRepository
) : UseCase<NoParams, Unit> {

    override fun invoke(params: NoParams): Result<Unit> {
        return try {
            repository.updateSelectedLanguage()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}