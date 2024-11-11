package com.jihaddmz.tasksync.feature_global.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DtoTask(
    @field:[Expose SerializedName("done")]
    val done: Boolean?,
    @field:[Expose SerializedName("icon")]
    val icon: String?,
    @field:[Expose SerializedName("id")]
    val id: Int?,
    @field:[Expose SerializedName("priority")]
    val priority: String?,
    @field:[Expose SerializedName("taskName")]
    val taskName: String?,
    @field:[Expose SerializedName("user")]
    val user: User?
) {

    data class User(
        @field:[Expose SerializedName("id")]
        val id: Int?,
        @field:[Expose SerializedName("password")]
        val password: String?,
        @field:[Expose SerializedName("username")]
        val username: String?
    )
}