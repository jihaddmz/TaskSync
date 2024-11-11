package com.jihaddmz.tasksync.feature_task.usecase

import com.google.gson.Gson
import com.jihaddmz.simplified_requests.SimplifiedRequests
import com.jihaddmz.tasksync.stateError
import com.jihaddmz.tasksync.feature_global.helper.BaseUseCase
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.DtoTask
import com.jihaddmz.tasksync.feature_global.model.ModelResponseError
import com.jihaddmz.tasksync.feature_task.model.ReqAddTask
import com.jihaddmz.tasksync.feature_task.model.ReqUpdateTaskIsDone
import com.jihaddmz.tasksync.stateLoading
import javax.inject.Inject

class UseCaseUpdateTaskIsDone @Inject constructor() : BaseUseCase<ReqUpdateTaskIsDone, DtoTask?>() {

    override suspend fun doWork(param: ReqUpdateTaskIsDone): DtoTask? {
        var modelResult: DtoTask? = null

        SimplifiedRequests.callPut<DtoTask>(
            "api/tasks/${param.id}",
            param.isDone,
            onSuccess = { result ->
                modelResult = result
            },
            onFailed = { exc ->
                stateError.value = Gson().fromJson(exc, ModelResponseError::class.java).detail
                return@callPut
            })

        return modelResult
    }
}