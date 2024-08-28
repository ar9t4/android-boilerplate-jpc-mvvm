package com.android.boilerplate.core.data.remote

/**
 * Created by Abdul Rahman on 16/05/2024
 */
object RemoteConfig {
    // remote network client config
    const val CONNECT_TIMEOUT: Long = 300
    const val WRITE_TIMEOUT: Long = 300
    const val READ_TIMEOUT: Long = 300

    // remote network request headers
    const val ACCEPT: String = "Accept"
    const val CONTENT_TYPE: String = "Content-Type"
    const val APPLICATION_JSON: String = "application/json"
    const val AUTHORIZATION: String = "Authorization"
    const val BEARER: String = "Bearer "
}