package com.android.boilerplate.features.themes.domain.usecase

import com.android.boilerplate.core.domain.usecase.UseCase
import com.android.boilerplate.features.themes.domain.repository.ThemesRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 09/07/2024
 */
class SetThemeUseCase @Inject constructor(
    private val repository: ThemesRepository
) : UseCase<Int, Unit> {

    override fun invoke(params: Int): Result<Unit> {
        return try {
            repository.setTheme(id = params)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}