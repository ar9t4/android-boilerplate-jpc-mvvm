package com.android.boilerplate.features.users.domain.usecase

import com.android.boilerplate.core.domain.model.NoParams
import com.android.boilerplate.core.domain.usecase.SuspendableUseCase
import com.android.boilerplate.features.users.domain.repository.UsersRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 22/08/2024
 */
class DeleteAllUsersUseCase @Inject constructor(private val repository: UsersRepository) :
    SuspendableUseCase<NoParams, Unit> {

    override suspend fun invoke(params: NoParams): Result<Unit> {
        return try {
            repository.deleteAllUsers()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}