package com.jihaddmz.tasksync.feature_task.usecase

import com.google.gson.Gson
import com.jihaddmz.simplified_requests.SimplifiedRequests
import com.jihaddmz.tasksync.stateError
import com.jihaddmz.tasksync.feature_global.helper.BaseUseCase
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.DtoTask
import com.jihaddmz.tasksync.feature_global.model.ModelResponseError
import com.jihaddmz.tasksync.feature_task.model.ReqAddTask
import com.jihaddmz.tasksync.stateLoading
import javax.inject.Inject

class UseCaseAddTask @Inject constructor() : BaseUseCase<ReqAddTask, DtoTask?>() {

    override suspend fun doWork(param: ReqAddTask): DtoTask? {
        var modelResult: DtoTask? = null

        SimplifiedRequests.callPost<DtoTask>(
            "api/tasks",
            body = param,
            onSuccess = { result ->
                modelResult = result
            },
            onFailed = { exc ->
                stateError.value = Gson().fromJson(exc, ModelResponseError::class.java).detail
                return@callPost
            })

        return modelResult
    }
}