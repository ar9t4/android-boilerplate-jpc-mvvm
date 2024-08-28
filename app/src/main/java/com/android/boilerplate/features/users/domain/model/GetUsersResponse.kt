package com.android.boilerplate.features.users.domain.model

import com.android.boilerplate.core.base.BaseParcelable
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 24/05/2024
 */
data class GetUsersResponse(
    @SerializedName("results") val results: List<RandomUser>? = null
) : BaseParcelable()