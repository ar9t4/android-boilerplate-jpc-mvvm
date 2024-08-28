package com.android.boilerplate.core.base

import android.content.Context
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import com.android.boilerplate.core.data.local.preferences.Preferences
import java.util.Locale

/**
 * Created by Abdul Rahman on 13/08/2024
 */
open class BaseActivity : ComponentActivity() {

    private fun getOverrideConfiguration(newBase: Context?): Configuration {
        val configuration = Configuration(newBase?.resources?.configuration)
        val appName = applicationInfo.loadLabel(packageManager).toString()
        val preferences = getSharedPreferences(appName, Context.MODE_PRIVATE)
        preferences.getString(Preferences.KEY_LANG, null)?.let {
            val locale = Locale(it)
            configuration.setLocale(locale)
        }
        return configuration
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        applyOverrideConfiguration(getOverrideConfiguration(newBase))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}