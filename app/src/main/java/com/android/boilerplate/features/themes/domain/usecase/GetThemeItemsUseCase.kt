package com.android.boilerplate.features.themes.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.themes.domain.model.Theme
import com.android.boilerplate.features.themes.domain.repository.ThemesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 07/06/2024
 */
class GetThemeItemsUseCase @Inject constructor(private val repository: ThemesRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<Theme>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<Theme>>> {
        return repository.getThemeItems()
    }
}