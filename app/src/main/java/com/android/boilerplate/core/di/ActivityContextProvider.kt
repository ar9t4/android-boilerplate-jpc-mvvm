package com.android.boilerplate.core.di

import android.app.Activity
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abdul Rahman on 16/08/2024
 */
@Singleton
class ActivityContextProvider @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var activityRef: WeakReference<Activity>? = null

    fun setActivity(activity: Activity) {
        activityRef = WeakReference(activity)
    }

    fun getActivityContext(): Context {
        return activityRef?.get() ?: context
    }
}