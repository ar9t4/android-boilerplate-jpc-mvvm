package com.android.boilerplate.common.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity

/**
 * Created by Abdul Rahman on 13/08/2024
 */
fun Context.getActivity(): ComponentActivity? {
    return try {
        when (this) {
            is ComponentActivity -> this
            is ContextWrapper -> this.baseContext.getActivity()
            else -> null
        }
    } catch (e: Exception) {
        null
    }
}