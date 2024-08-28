package com.android.boilerplate.features.themes.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.themes.domain.model.Theme
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface ThemesRepository {
    suspend fun getThemeItems(): Flow<Resource<List<Theme>>>
    fun setTheme(id: Int)
}