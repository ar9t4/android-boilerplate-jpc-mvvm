package com.android.boilerplate.features.languages.domain.model

import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 07/06/2024
 */
data class Language(
    @SerializedName("id") val id: Int,
    @SerializedName("lang") val lang: String,
    @SerializedName("name") val name: String,
    @SerializedName("selected") val selected: Boolean = false
) : BaseParcelable()