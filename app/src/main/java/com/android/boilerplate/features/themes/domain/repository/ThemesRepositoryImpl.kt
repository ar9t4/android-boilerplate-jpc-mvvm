package com.android.boilerplate.features.themes.domain.repository

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.themes.domain.model.Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class ThemesRepositoryImpl @Inject constructor(
    private val activityContextProvider: ActivityContextProvider,
    private val preferences: Preferences
) : ThemesRepository {

    private val themeItems = mutableListOf<Theme>()
    private val context: Context get() = activityContextProvider.getActivityContext()

    override suspend fun getThemeItems(): Flow<Resource<List<Theme>>> {
        return flow {
            if (themeItems.isEmpty()) {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
            }
            context.apply {
                // get selected theme from preferences
                val selectedThemeId = preferences.getInt(Preferences.KEY_THEME)
                val items =  listOf(
                    Theme(id = 0, name = getString(R.string.system_default), selected = selectedThemeId == 0),
                    Theme(id = 1, name = getString(R.string.light), selected = selectedThemeId == 1),
                    Theme(id = 2, name = getString(R.string.dark), selected = selectedThemeId == 2)
                )
                themeItems.clear()
                themeItems.addAll(items)
                emit(value = Resource.Success(data = items))
            }
        }
    }

    override fun setTheme(id: Int) {
        // set user selected theme in preferences
        preferences.setInt(Preferences.KEY_THEME, id)
        // update user selected theme in ui state
        updateSelectedTheme(id = id)
    }

    private fun updateSelectedTheme(id: Int) {
        themeItems.forEachIndexed { index, theme ->
            themeItems[index] = theme.copy(selected = theme.id == id)
        }
    }
}