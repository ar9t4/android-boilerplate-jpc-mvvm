package com.android.boilerplate.features.more.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.more.domain.model.More
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface MoreRepository {
    suspend fun getMoreItems(): Flow<Resource<List<More>>>
}