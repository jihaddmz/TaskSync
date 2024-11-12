package com.jihaddmz.tasksync.feature_global.model

sealed class TypeBottomSheet() {

    class TaskActions(val id: Int, val text: String) : TypeBottomSheet()
    class ViewTasksByCategory(val tasks: List<DtoCategory.Task?>) : TypeBottomSheet()
    data object None: TypeBottomSheet()
    data object Add: TypeBottomSheet()
    data object Categories: TypeBottomSheet()
    data object Logout: TypeBottomSheet()
    data object Username: TypeBottomSheet()
    data object Password: TypeBottomSheet()
}