package com.android.boilerplate.core.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Created by Abdul Rahman on 23/05/2024
 */

/**
 * use this for non-suspendable kind of operations
 * e.g. application logic validations, form validations, business logic validations and etc.
 */
internal interface UseCase<in Params : Any, out T : Any> {
    operator fun invoke(params: Params): Result<T>
}

/**
 * use this for suspendable kind of operations with no results
 * e.g. post data to remote with no results, write data into local database/file and etc.
 */
internal interface SuspendableUseCase<in Params : Any, out T : Any> {
    suspend operator fun invoke(params: Params): Result<T>
}

/**
 * use this for suspendable kind of operations with result
 * e.g. get data from remote, read data from local database/file and etc.
 */
internal interface SuspendableUseCaseWithResult<in Params : Any, out T : Any> {
    suspend operator fun invoke(params: Params): Flow<T>
}

/**
 * use this for observable kind of operations
 * e.g. data flows (data streams) or user live location updates and etc.
 */
internal interface ObservableUseCase<in Params : Any, out T : Any> {
    operator fun invoke(params: Params): Flow<T>
}