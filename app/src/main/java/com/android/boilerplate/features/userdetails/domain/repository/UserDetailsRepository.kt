package com.android.boilerplate.features.userdetails.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface UserDetailsRepository{
    suspend fun getUser(id: Int): Flow<Resource<RandomUser>>
}