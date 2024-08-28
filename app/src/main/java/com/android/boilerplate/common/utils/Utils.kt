package com.android.boilerplate.common.utils

import android.os.Build

/**
 * Created by Abdul Rahman on 05/07/2024
 */
object Utils {
    fun isM() = Build.VERSION.SDK_INT == Build.VERSION_CODES.M
    fun isMPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    fun isN() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N
    fun isNPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    fun isP() = Build.VERSION.SDK_INT == Build.VERSION_CODES.P
    fun isPPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    fun isQ() = Build.VERSION.SDK_INT == Build.VERSION_CODES.Q
    fun isQPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    fun isR() = Build.VERSION.SDK_INT == Build.VERSION_CODES.R
    fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    fun isS() = Build.VERSION.SDK_INT == Build.VERSION_CODES.S
    fun isSPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    fun isSV2() = Build.VERSION.SDK_INT == Build.VERSION_CODES.S_V2
    fun isSV2Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2
    fun isTiramisu() = Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU
    fun isTiramisuPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}