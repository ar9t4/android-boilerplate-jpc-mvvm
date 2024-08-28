package com.android.boilerplate.features.settings.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.settings.domain.model.Setting
import com.android.boilerplate.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 07/06/2024
 */
class GetSettingItemsUseCase @Inject constructor(private val repository: SettingsRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<Setting>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<Setting>>> {
        return repository.getSettingItems()
    }
}