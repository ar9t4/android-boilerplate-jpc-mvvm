package com.android.boilerplate.core.data.local.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.boilerplate.core.base.BaseParcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Entity
data class RandomUser(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("uid") var id: Int? = null,
    @SerializedName("gender") var gender: String? = null,
    @Embedded
    @SerializedName("name") var name: Name? = null,
    @Embedded
    @SerializedName("location") var location: Location? = null,
    @SerializedName("email") var email: String? = null,
    @Embedded
    @SerializedName("dob") var dob: Dob? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("cell") var cell: String? = null,
    @Embedded
    @SerializedName("picture") var picture: Picture? = null,
    @SerializedName("accessToken") var accessToken: String? = null
) : BaseParcelable()

data class Name(
    @SerializedName("title") var title: String? = null,
    @SerializedName("first") var first: String? = null,
    @SerializedName("last") var last: String? = null
) : BaseParcelable()

data class Location(
    @Embedded
    @SerializedName("street") var street: Street? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("postcode") var postcode: String? = null,
    @Embedded
    @SerializedName("coordinates") var coordinates: Coordinates? = null
) : BaseParcelable()

data class Street(
    @SerializedName("number") var number: Int? = null,
    @SerializedName("name") var name: String? = null
) : BaseParcelable()

data class Coordinates(
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null
) : BaseParcelable()

data class Dob(
    @SerializedName("date") var date: String? = null,
    @SerializedName("age") var age: Int? = null
) : BaseParcelable()

data class Picture(
    @SerializedName("large") var large: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
) : BaseParcelable()