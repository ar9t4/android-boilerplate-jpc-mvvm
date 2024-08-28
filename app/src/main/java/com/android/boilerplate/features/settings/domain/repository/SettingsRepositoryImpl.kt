package com.android.boilerplate.features.settings.domain.repository

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.common.workers.PeriodicWorkerUtils
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.languages.domain.model.Language
import com.android.boilerplate.features.settings.domain.model.Setting
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class SettingsRepositoryImpl @Inject constructor(
    private val activityContextProvider: ActivityContextProvider,
    private val preferences: Preferences,
    private val gson: Gson
) : SettingsRepository {

    private val settingItems = mutableListOf<Setting>()
    private val context: Context get() = activityContextProvider.getActivityContext()

    override suspend fun getSettingItems(): Flow<Resource<List<Setting>>> {
        return flow {
            if (settingItems.isEmpty()) {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
            }
            context.apply {
                val items = listOf(
                    Setting(
                        id = 0,
                        key = getString(R.string.notifications),
                        value = getSelectedNotificationStateName()
                    ),
                    Setting(
                        id = 1,
                        key = getString(R.string.theme),
                        value = getSelectedThemeName()
                    ),
                    Setting(
                        id = 2,
                        key = getString(R.string.language),
                        value = getSelectedLanguageName()
                    )
                )
                settingItems.clear()
                settingItems.addAll(items)
                emit(value = Resource.Success(data = items))
            }
        }
    }

    private fun getSelectedNotificationStateName(): String {
        val selectedNotificationsState = preferences.getBoolean(Preferences.KEY_NOTIFICATION)
        return if (selectedNotificationsState) context.getString(R.string.setting_on)
        else context.getString(R.string.setting_off)
    }

    private fun getSelectedThemeName(): String {
        val selectedThemeId = preferences.getInt(Preferences.KEY_THEME)
        return when (selectedThemeId) {
            0 -> context.getString(R.string.system_default)
            1 -> context.getString(R.string.light)
            else -> context.getString(R.string.dark)
        }
    }

    private fun getSelectedLanguageName(): String {
        val selectedLanguageCode = preferences.getString(Preferences.KEY_LANG)
        val fallbackLanguageName = context.getString(R.string.english)
        context.apply {
            // parse json file
            val json = assets.open("languages.json").bufferedReader()
                .use { it.readText() }
            val type = object : TypeToken<List<Language>>() {}.type
            gson.fromJson<List<Language>>(json, type)?.let { languages ->
                if (languages.isNotEmpty()) {
                    languages.find { it.lang == selectedLanguageCode }?.let { return it.name }
                        ?: return fallbackLanguageName
                }
            }
        }
        return fallbackLanguageName
    }

    override fun toggleNotification() {
        val notificationState = preferences.getBoolean(Preferences.KEY_NOTIFICATION)
        if (notificationState) {
            // turn off notifications
            PeriodicWorkerUtils.cancelPeriodicWorker(context)
        } else {
            // turn on notifications
            PeriodicWorkerUtils.createPeriodicWorker(context)
        }
        // toggle notifications state
        preferences.setBoolean(Preferences.KEY_NOTIFICATION, !notificationState)
        // update user selected notifications state in ui state
        val index =
            settingItems.indexOfFirst { it.key == context.getString(R.string.notifications) }
        if (index != -1) {
            val settingItem = settingItems[index]
            settingItems[index] = settingItem.copy(value = getSelectedNotificationStateName())
        }
    }

    override fun updateSelectedTheme() {
        // update user selected theme in ui state
        val index = settingItems.indexOfFirst { it.key == context.getString(R.string.theme) }
        if (index != -1) {
            val settingItem = settingItems[index]
            settingItems[index] = settingItem.copy(value = getSelectedThemeName())
        }
    }

    override fun updateSelectedLanguage() {
        // update user selected language in ui state
        val index = settingItems.indexOfFirst { it.key == context.getString(R.string.language) }
        if (index != -1) {
            val settingItem = settingItems[index]
            settingItems[index] = settingItem.copy(value = getSelectedLanguageName())
        }
    }
}