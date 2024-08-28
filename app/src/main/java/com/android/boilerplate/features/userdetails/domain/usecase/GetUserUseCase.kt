package com.android.boilerplate.features.userdetails.domain.usecase

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.core.domain.usecase.SuspendableUseCaseWithResult
import com.android.boilerplate.features.userdetails.domain.repository.UserDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 22/08/2024
 */
class GetUserUseCase @Inject constructor(private val repository: UserDetailsRepository) :
    SuspendableUseCaseWithResult<Int, Resource<RandomUser>> {

    override suspend fun invoke(params: Int): Flow<Resource<RandomUser>> {
        return repository.getUser(id = params)
    }
}