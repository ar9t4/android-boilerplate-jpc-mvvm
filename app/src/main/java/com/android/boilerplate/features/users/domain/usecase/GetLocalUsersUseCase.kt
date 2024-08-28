package com.android.boilerplate.features.users.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.users.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 22/08/2024
 */
class GetLocalUsersUseCase @Inject constructor(private val repository: UsersRepository) :
    SuspendableUseCaseWithResult<NoParams, Resource<List<RandomUser>>> {

    override suspend fun invoke(params: NoParams): Flow<Resource<List<RandomUser>>> {
        return repository.getLocalUsers()
    }
}