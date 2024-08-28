package com.android.boilerplate.common.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.boilerplate.R
import com.android.boilerplate.common.utils.NotificationUtils
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.google.gson.Gson

/**
 * Created by Abdul Rahman on 05/07/2024
 */
class PeriodicWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        val preferences = Preferences(gson = Gson(), context = applicationContext)
        return if (preferences.getBoolean(Preferences.KEY_NOTIFICATION)) {
            NotificationUtils.sendNotification(
                applicationContext,
                applicationContext.getString(R.string.notification_title),
                applicationContext.getString(R.string.notification_text)
            )
            Result.success()
        } else {
            PeriodicWorkerUtils.cancelPeriodicWorker(applicationContext)
            Result.failure()
        }
    }
}