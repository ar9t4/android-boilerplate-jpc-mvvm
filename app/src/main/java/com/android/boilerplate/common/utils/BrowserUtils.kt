package com.android.boilerplate.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.UriHandler

/**
 * Created by Abdul Rahman on 11/06/2024
 */
object BrowserUtils {

    fun openUri(uriHandler: UriHandler, uri: String) {
        uriHandler.openUri(uri = uri)
    }

    fun launchRateUsUri(context: Context, uriHandler: UriHandler) {
        try {
            val uri = "market://details?id=${context.packageName}"
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
        } catch (e: Exception) {
            val uri = "http://play.google.com/store/apps/details?id=${context.packageName}"
            openUri(uriHandler = uriHandler, uri = uri)
        }
    }

    fun launchMoreAppsUri(context: Context, uriHandler: UriHandler) {
        try {
            val uri = "market://search?q=pub:XYZ"
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
        } catch (e: Exception) {
            val uri = "http://play.google.com/store/search?q=pub:XYZ"
            openUri(uriHandler = uriHandler, uri = uri)
        }
    }
}