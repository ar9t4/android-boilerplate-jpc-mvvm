package com.android.boilerplate.features.feedback.domain.model

import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 11/06/2024
 */
data class Feedback(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("selected") val selected: Boolean = false,
) : BaseParcelable()
