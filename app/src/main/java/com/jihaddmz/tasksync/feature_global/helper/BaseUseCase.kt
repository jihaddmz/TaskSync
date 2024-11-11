package com.jihaddmz.tasksync.feature_global.helper

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseUseCase<P, R> {

    abstract suspend fun doWork(param: P): R?

    suspend fun run(param: P, data: MutableState<R?>) {
        data.value = doWork(param)
    }

}