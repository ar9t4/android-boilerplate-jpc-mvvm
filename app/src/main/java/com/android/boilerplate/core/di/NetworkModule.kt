package com.android.boilerplate.core.di

import com.android.boilerplate.BuildConfig
import com.android.boilerplate.core.data.local.preferences.Preferences
import com.android.boilerplate.core.data.remote.RemoteApi
import com.android.boilerplate.core.data.remote.RemoteConfig
import com.android.boilerplate.core.di.qualifiers.AuthInterceptor
import com.android.boilerplate.core.di.qualifiers.LoggingInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Abdul Rahman on 15/05/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @LoggingInterceptor
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @AuthInterceptor
    @Provides
    fun provideAuthInterceptor(preferences: Preferences): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(RemoteConfig.CONTENT_TYPE, RemoteConfig.APPLICATION_JSON)
                .addHeader(RemoteConfig.ACCEPT, RemoteConfig.APPLICATION_JSON)
            preferences.getSignInUser()?.accessToken?.let {
                request.addHeader(
                    RemoteConfig.AUTHORIZATION,
                    RemoteConfig.BEARER.plus(it)
                )
            }
            chain.proceed(request.build())
        }
    }

    @Provides
    fun provideOkHttpClient(
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @AuthInterceptor authInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(RemoteConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RemoteConfig.WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RemoteConfig.READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRemoteApi(client: OkHttpClient): RemoteApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(RemoteApi::class.java)
    }
}