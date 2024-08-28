package com.android.boilerplate.common.workers

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.android.boilerplate.BuildConfig
import java.util.concurrent.TimeUnit

/**
 * Created by Abdul Rahman on 05/07/2024
 */
object PeriodicWorkerUtils {

    fun createPeriodicWorker(context: Context) {
        val periodicWorkRequest = if (BuildConfig.DEBUG) {
            PeriodicWorkRequest
                .Builder(PeriodicWorker::class.java, 1, TimeUnit.DAYS)
                .addTag(PeriodicWorker::class.java.simpleName)
                .build()
        } else {
            PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 7, TimeUnit.DAYS)
                .addTag(PeriodicWorker::class.java.simpleName)
                .build()
        }
        WorkManager.getInstance(context).enqueue(periodicWorkRequest)
    }

    fun cancelPeriodicWorker(context: Context) {
        WorkManager.getInstance(context).cancelAllWorkByTag(PeriodicWorker::class.java.simpleName)
    }
}