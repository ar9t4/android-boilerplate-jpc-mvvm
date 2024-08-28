package com.android.boilerplate.features.settings.domain.model

import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 07/06/2024
 */
data class Setting(
    @SerializedName("id") val id: Int,
    @SerializedName("key") val key: String,
    @SerializedName("value") val value: String
) : BaseParcelable()