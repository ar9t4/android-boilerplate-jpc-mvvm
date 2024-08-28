package com.android.boilerplate.features.more.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.more.domain.model.More
import com.android.boilerplate.features.more.domain.repository.MoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 06/06/2024
 */
class GetMoreItemsUseCase @Inject constructor(private val repository: MoreRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<More>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<More>>> {
        return repository.getMoreItems()
    }
}