package com.jihaddmz.tasksync.feature_global.helper

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jihaddmz.tasksync.stateLoading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <P, R> executeUseCase(
        useCase: BaseUseCase<P, R>,
        param: P,
        data: MutableState<R?>
    ) {
        stateLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            useCase.run(param, data)
            stateLoading.value = false
        }

    }
}