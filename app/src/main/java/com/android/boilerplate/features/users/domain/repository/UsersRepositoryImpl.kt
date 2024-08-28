package com.android.boilerplate.features.users.domain.repository

import com.android.boilerplate.core.base.BaseRepository
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.daos.RandomUserDao
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.android.boilerplate.core.data.remote.RemoteApi
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.users.domain.model.GetUsersResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class UsersRepositoryImpl @Inject constructor(
    activityContextProvider: ActivityContextProvider,
    private val randomUserDao: RandomUserDao,
    private val remoteApi: RemoteApi
) : BaseRepository(activityContextProvider = activityContextProvider), UsersRepository {

    override suspend fun getLocalUsers(): Flow<Resource<List<RandomUser>>> {
        return flow {
            try {
                emit(value = Resource.Success(data = randomUserDao.getUsers()))
            } catch (e: Exception) {
                emit(value = Resource.Error(statusCode = -1, message = e.message ?: e.toString()))
            }
        }
    }

    override suspend fun getRemoteUsers(results: Int): Flow<Resource<GetUsersResponse>> {
        return execute { remoteApi.getUsers(results = results) }
    }

    override suspend fun insertUsers(users: List<RandomUser>) {
        randomUserDao.insert(users = users)
    }

    override suspend fun deleteAllUsers() {
        randomUserDao.deleteAllUsers()
    }
}