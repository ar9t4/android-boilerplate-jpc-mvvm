package com.android.boilerplate.features.userdetails.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.daos.RandomUserDao
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class UserDetailsRepositoryImpl @Inject constructor(private val randomUserDao: RandomUserDao) :
    UserDetailsRepository {

    override suspend fun getUser(id: Int): Flow<Resource<RandomUser>> {
        return flow {
            try {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
                emit(value = Resource.Success(data = randomUserDao.getUser(id = id)))
            } catch (e: Exception) {
                emit(value = Resource.Error(statusCode = -1, message = e.message ?: e.toString()))
            }
        }
    }
}