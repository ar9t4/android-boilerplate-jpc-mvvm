package com.android.boilerplate.core.data.local.preferences

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.core.data.local.database.entities.RandomUser
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Singleton
class Preferences @Inject constructor(
    private val gson: Gson,
    @ApplicationContext private val context: Context,
) {
    private val sharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun contains(key: String): Boolean = sharedPreferences.contains(key)

    fun setString(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun setFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, -1.0F)
    }

    fun setLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, -1L)
    }

    fun setSignInUser(user: RandomUser) {
        sharedPreferences.edit().putString("user", gson.toJson(user)).apply()
    }

    fun getSignInUser(): RandomUser? {
        val user = sharedPreferences.getString("user", null)
        return gson.fromJson(user, RandomUser::class.java)
    }

    companion object {
        const val KEY_DEFAULT = "default"
        const val KEY_NOTIFICATION = "notification"
        const val KEY_THEME = "theme"
        const val KEY_LANG = "lang"
    }
}