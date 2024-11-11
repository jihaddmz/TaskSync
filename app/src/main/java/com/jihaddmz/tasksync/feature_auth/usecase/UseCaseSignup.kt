package com.jihaddmz.tasksync.feature_auth.usecase

import com.google.gson.Gson
import com.jihaddmz.simplified_requests.SimplifiedRequests
import com.jihaddmz.tasksync.stateError
import com.jihaddmz.tasksync.feature_auth.model.DtoUser
import com.jihaddmz.tasksync.feature_auth.model.ReqUser
import com.jihaddmz.tasksync.feature_global.helper.BaseUseCase
import com.jihaddmz.tasksync.feature_global.model.ModelResponseError
import javax.inject.Inject

class UseCaseSignup @Inject constructor() : BaseUseCase<ReqUser, DtoUser?>() {

    override suspend fun doWork(param: ReqUser): DtoUser? {
        var modelResult: DtoUser? = null

        SimplifiedRequests.callPost<DtoUser>(
            "api/users/signup",
            param,
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