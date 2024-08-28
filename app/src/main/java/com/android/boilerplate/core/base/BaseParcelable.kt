package com.android.boilerplate.core.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Abdul Rahman on 15/05/2024
 */

/**
 * use this as base class of the data class for serialization/de-serialization purposes
 */
@Parcelize
open class BaseParcelable : Parcelable