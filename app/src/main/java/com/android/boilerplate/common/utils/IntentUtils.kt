package com.android.boilerplate.common.utils

import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by Abdul Rahman on 11/06/2024
 */
object IntentUtils {

    private const val TAG = "IntentUtils"

    fun launchIntent(context: Context, title: String, message: String) {
        try {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
            }
            context.startActivity(Intent.createChooser(shareIntent, title))
        } catch (exception: Exception) {
            Log.e(TAG, "launchIntent: $exception")
        }
    }
}