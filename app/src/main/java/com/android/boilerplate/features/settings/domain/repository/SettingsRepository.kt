package com.android.boilerplate.features.settings.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.settings.domain.model.Setting
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface SettingsRepository {
    suspend fun getSettingItems(): Flow<Resource<List<Setting>>>
    fun toggleNotification()
    fun updateSelectedTheme()
    fun updateSelectedLanguage()
}