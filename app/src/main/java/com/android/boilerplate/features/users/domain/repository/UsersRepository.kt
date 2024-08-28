package com.android.boilerplate.features.users.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.features.users.domain.model.GetUsersResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface UsersRepository {
    suspend fun getLocalUsers(): Flow<Resource<List<RandomUser>>>
    suspend fun getRemoteUsers(results: Int): Flow<Resource<GetUsersResponse>>
    suspend fun insertUsers(users: List<RandomUser>)
    suspend fun deleteAllUsers()
}