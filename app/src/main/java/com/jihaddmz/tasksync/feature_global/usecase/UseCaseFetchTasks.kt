package com.jihaddmz.tasksync.feature_global.usecase

import com.google.gson.Gson
import com.jihaddmz.simplified_requests.SimplifiedRequests
import com.jihaddmz.tasksync.stateError
import com.jihaddmz.tasksync.feature_global.helper.BaseUseCase
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.DtoTask
import com.jihaddmz.tasksync.feature_global.model.ModelResponseError
import com.jihaddmz.tasksync.stateLoading
import javax.inject.Inject

class UseCaseFetchTasks @Inject constructor() : BaseUseCase<String, List<DtoTask>?>() {

    override suspend fun doWork(param: String): List<DtoTask>? {
        var modelResult: List<DtoTask>? = null

        SimplifiedRequests.callGet<List<DtoTask>?>(
            "api/tasks",
            hashMapOf("username" to param),
            onSuccess = { result ->
                modelResult = result
            },
            onFailed = { exc ->
                stateError.value = Gson().fromJson(exc, ModelResponseError::class.java).detail
                return@callGet
            })

        return modelResult
    }
}