package com.android.boilerplate.features.languages.domain.usecase

import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.languages.domain.model.Language
import com.android.boilerplate.features.languages.domain.repository.LanguagesRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 15/07/2024
 */
class SetLanguageUseCase @Inject constructor(
    private val repository: LanguagesRepository
) : UseCase<Language, Unit> {

    override fun invoke(params: Language): Result<Unit> {
        return try {
            repository.setLanguage(selectedLanguage = params)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}