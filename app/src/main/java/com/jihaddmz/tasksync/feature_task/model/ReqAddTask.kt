package com.jihaddmz.tasksync.feature_task.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ReqAddTask(
    @field:[Expose SerializedName("categoryTitle")]
    val categoryTitle: String?,
    @field:[Expose SerializedName("icon")]
    val icon: String?,
    @field:[Expose SerializedName("priority")]
    val priority: String?,
    @field:[Expose SerializedName("taskName")]
    val taskName: String?,
    @field:[Expose SerializedName("username")]
    val username: String?
)