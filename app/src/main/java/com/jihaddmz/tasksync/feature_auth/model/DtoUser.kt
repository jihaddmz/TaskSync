package com.jihaddmz.tasksync.feature_auth.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DtoUser(
    @field:[Expose SerializedName("id")]
    val id: Int?,
    @field:[Expose SerializedName("password")]
    val password: String?,
    @field:[Expose SerializedName("username")]
    val username: String?
)