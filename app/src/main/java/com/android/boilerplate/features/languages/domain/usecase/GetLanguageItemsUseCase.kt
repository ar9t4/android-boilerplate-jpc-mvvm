package com.android.boilerplate.features.languages.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.languages.domain.model.Language
import com.android.boilerplate.features.languages.domain.repository.LanguagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 07/06/2024
 */
class GetLanguageItemsUseCase @Inject constructor(private val repository: LanguagesRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<Language>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<Language>>> {
        return repository.getLanguageItems()
    }
}