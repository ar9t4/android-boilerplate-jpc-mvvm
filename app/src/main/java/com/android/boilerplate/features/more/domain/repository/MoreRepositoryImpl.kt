package com.android.boilerplate.features.more.domain.repository

import android.content.Context
import com.android.boilerplate.R
import com.android.boilerplate.core.base.Resource
import com.android.boilerplate.core.di.ActivityContextProvider
import com.android.boilerplate.features.more.domain.model.More
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Abdul Rahman on 14/05/2024
 */
class MoreRepositoryImpl @Inject constructor(
    private val activityContextProvider: ActivityContextProvider
) : MoreRepository {

    private val moreItems = mutableListOf<More>()
    private val context: Context get() = activityContextProvider.getActivityContext()

    override suspend fun getMoreItems(): Flow<Resource<List<More>>> {
        return flow {
            if (moreItems.isEmpty()) {
                emit(value = Resource.Loading(isLoading = true))
                delay(500)
            }
            context.apply {
                val items = listOf(
                    More(1, getString(R.string.settings), R.drawable.ic_settings),
                    More(2, getString(R.string.feedback), R.drawable.ic_feedback),
                    More(3, getString(R.string.privacy_policy), R.drawable.ic_privacy_policy),
                    More(4, getString(R.string.share), R.drawable.ic_share),
                    More(5, getString(R.string.rate_us), R.drawable.ic_rate),
                    More(6, getString(R.string.more_apps), R.drawable.ic_more_apps),
                    More(7, getString(R.string.version), R.drawable.ic_version)
                )
                moreItems.clear()
                moreItems.addAll(items)
                emit(value = Resource.Success(data = items))
            }
        }
    }
}