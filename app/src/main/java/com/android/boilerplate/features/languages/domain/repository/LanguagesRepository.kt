package com.android.boilerplate.features.languages.domain.repository

import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.features.languages.domain.model.Language
import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 14/05/2024
 */
interface LanguagesRepository {
    suspend fun getLanguageItems(): Flow<Resource<List<Language>>>
    fun setLanguage(selectedLanguage: Language)
}