package com.android.boilerplate.features.more.domain.model

import androidx.annotation.DrawableRes
import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 06/06/2024
 */
data class More(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("icon") @DrawableRes val icon: Int,
) : BaseParcelable()