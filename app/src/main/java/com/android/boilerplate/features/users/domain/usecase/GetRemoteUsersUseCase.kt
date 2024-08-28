package com.android.boilerplate.features.users.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.users.domain.model.GetUsersRequest
import com.android.boilerplate.features.users.domain.model.GetUsersResponse
import com.android.boilerplate.features.users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 22/08/2024
 */
class GetRemoteUsersUseCase @Inject constructor(private val repository: UsersRepository) :
    SuspendableUseCaseWithResult<GetUsersRequest, Resource<GetUsersResponse>> {

    override suspend fun invoke(params: GetUsersRequest): Flow<Resource<GetUsersResponse>> {
        return repository.getRemoteUsers(results = params.results)
    }
}