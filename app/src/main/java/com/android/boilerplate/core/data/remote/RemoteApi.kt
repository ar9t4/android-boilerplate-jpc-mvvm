package com.android.boilerplate.core.data.remote

import com.android.boilerplate.features.users.domain.model.GetUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Abdul Rahman on 16/05/2024
 */
interface RemoteApi {
    @GET(RemoteEndPoints.GET_USERS)
    suspend fun getUsers(@Query("results") results: Int): Response<GetUsersResponse>
}