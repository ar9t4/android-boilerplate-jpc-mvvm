package com.android.boilerplate.features.users.domain.model

import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 24/05/2024
 */
data class GetUsersRequest(
    @SerializedName("results") val results: Int
) : BaseParcelable()