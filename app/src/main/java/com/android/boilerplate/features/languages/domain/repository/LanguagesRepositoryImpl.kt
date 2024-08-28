package com.android.boilerplate.features.languages.domain.repository

import android.content.Context
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.languages.domain.model.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class LanguagesRepositoryImpl @Inject constructor(
    private val activityContextProvider: ActivityContextProvider,
    private val preferences: Preferences,
    private val gson: Gson
) : LanguagesRepository {

    private val languageItems = mutableListOf<Language>()
    private val context: Context get() = activityContextProvider.getActivityContext()

    override suspend fun getLanguageItems(): Flow<Resource<List<Language>>> {
        return flow {
            if (languageItems.isEmpty()) {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
                context.apply {
                    // parse json file
                    val json = assets.open("languages.json").bufferedReader()
                        .use { it.readText() }
                    val type = object : TypeToken<List<Language>>() {}.type
                    gson.fromJson<List<Language>>(json, type)?.let { languages ->
                        if (languages.isNotEmpty()) {
                            val selectedLanguageCode = preferences.getString(Preferences.KEY_LANG)
                            val mutableLanguages = languages.toMutableList()
                            mutableLanguages.forEachIndexed { index, language ->
                                mutableLanguages[index] = language.copy(
                                    selected = language.lang == selectedLanguageCode
                                )
                            }
                            languageItems.addAll(mutableLanguages)
                        }
                    }
                }
            }
            emit(value = Resource.Success(data = languageItems.toMutableList()))
        }
    }

    override fun setLanguage(selectedLanguage: Language) {
        // set user selected language in preferences
        preferences.setString(Preferences.KEY_LANG, selectedLanguage.lang)
        // update user selected language in ui state
        updateSelectedLanguage(selectedLanguage = selectedLanguage)
    }

    private fun updateSelectedLanguage(selectedLanguage: Language) {
        languageItems.forEachIndexed { index, language ->
            languageItems[index] = language.copy(selected = language.id == selectedLanguage.id)
        }
    }
}