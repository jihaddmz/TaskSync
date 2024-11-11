package com.jihaddmz.tasksync.feature_global.usecase

import com.google.gson.Gson
import com.jihaddmz.simplified_requests.SimplifiedRequests
import com.jihaddmz.tasksync.stateError
import com.jihaddmz.tasksync.feature_global.helper.BaseUseCase
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.ModelResponseError
import com.jihaddmz.tasksync.stateLoading
import javax.inject.Inject

class UseCaseFetchCategories @Inject constructor() : BaseUseCase<String, List<DtoCategory>?>() {

    override suspend fun doWork(param: String): List<DtoCategory>? {
        var modelResult: List<DtoCategory>? = null

        SimplifiedRequests.callGet<List<DtoCategory>?>(
            "api/categories",
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