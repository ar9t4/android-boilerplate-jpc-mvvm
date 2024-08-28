package com.android.boilerplate.features.users.domain.usecase

import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.core.domain.usecase.SuspendableUseCase
import com.android.boilerplate.features.users.domain.repository.UsersRepository
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 22/08/2024
 */
class InsertUsersUseCase @Inject constructor(private val repository: UsersRepository) :
    SuspendableUseCase<List<RandomUser>, Unit> {

    override suspend fun invoke(params: List<RandomUser>): Result<Unit> {
        return try {
            repository.insertUsers(users = params)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}