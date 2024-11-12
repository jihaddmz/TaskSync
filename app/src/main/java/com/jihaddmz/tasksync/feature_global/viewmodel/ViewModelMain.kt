package com.jihaddmz.tasksync.feature_global.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jihaddmz.tasksync.feature_global.helper.BaseViewModel
import com.jihaddmz.tasksync.feature_global.helper.HelperSharedPreference
import com.jihaddmz.tasksync.feature_global.model.DtoCategory
import com.jihaddmz.tasksync.feature_global.model.DtoTask
import com.jihaddmz.tasksync.feature_global.usecase.UseCaseFetchCategories
import com.jihaddmz.tasksync.feature_global.usecase.UseCaseFetchTasks
import com.jihaddmz.tasksync.feature_task.model.ReqAddTask
import com.jihaddmz.tasksync.feature_task.model.ReqUpdateTaskIsDone
import com.jihaddmz.tasksync.feature_task.usecase.UseCaseAddTask
import com.jihaddmz.tasksync.feature_task.usecase.UseCaseDeleteTask
import com.jihaddmz.tasksync.feature_task.usecase.UseCaseUpdateTaskIsDone
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(
    private val useCaseFetchCategories: UseCaseFetchCategories,
    private val useCaseFetchTasks: UseCaseFetchTasks,
    private val useCaseAddTask: UseCaseAddTask,
    private val useCaseUpdateTaskIsDone: UseCaseUpdateTaskIsDone,
    private val useCaseDeleteTask: UseCaseDeleteTask
) : BaseViewModel() {

    val stateListOfCategories: MutableState<List<DtoCategory>?> = mutableStateOf(
        null
    )
    val stateListOfTasks: MutableState<List<DtoTask>?> = mutableStateOf(null)
    val stateAddTask: MutableState<DtoTask?> = mutableStateOf(null)
    val stateUpdateTaskIsDone: MutableState<DtoTask?> = mutableStateOf(null)
    val stateDeleteTask: MutableState<DtoTask?> = mutableStateOf(null)


    val taskName = mutableStateOf("")
    val priority = mutableStateOf("Low")
    val category = mutableStateOf("")

    val username: MutableState<String>
        get() = mutableStateOf(HelperSharedPreference.get {
            getString(
                HelperSharedPreference.KEY_USERNAME,
                ""
            ) ?: ""
        })

    fun deleteTask(id: Int) {
        executeUseCase(useCaseDeleteTask, id, stateDeleteTask)
    }

    fun updateTaskDone(id: Int, isDone: Boolean) {
        executeUseCase(useCaseUpdateTaskIsDone, ReqUpdateTaskIsDone(id, isDone), stateUpdateTaskIsDone)
    }

    fun addTask() {
        executeUseCase(
            useCaseAddTask,
            ReqAddTask(category.value, "", priority.value, taskName.value, username.value),
            stateAddTask
        )
        category.value = ""
        taskName.value = ""
        priority.value = "Low"
    }

    fun fetchCategories() {
        executeUseCase(useCaseFetchCategories, username.value, stateListOfCategories)
    }

    fun fetchTasks() {
        executeUseCase(useCaseFetchTasks, username.value, stateListOfTasks)

    }

    fun signOut() {
        HelperSharedPreference.save {
            remove(HelperSharedPreference.KEY_USERNAME)
            remove(HelperSharedPreference.KEY_PASSWORD)
        }
    }

    fun calculatePercentage(): Int {
        val totalTasks = stateListOfCategories.value?.flatMap { it.tasks ?: listOf() }
        val doneTasks = totalTasks?.filter { it?.done == true }

        if (totalTasks.isNullOrEmpty()) {
            return 0
        } else {
            return doneTasks!!.size * 100 / totalTasks.size
        }
    }

    fun filterTaskList(enteredText: String) {
        stateListOfTasks.value = stateListOfTasks.value?.filter {
            it.taskName!!.lowercase().contains(enteredText.lowercase())
        } ?: listOf()
    }
}