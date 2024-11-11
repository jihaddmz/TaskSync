package com.jihaddmz.tasksync.feature_global.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ModelResponseError(
    @field:[Expose SerializedName("detail")]
    val detail: String?,
    @field:[Expose SerializedName("instance")]
    val instance: String?,
    @field:[Expose SerializedName("status")]
    val status: Int?,
    @field:[Expose SerializedName("title")]
    val title: String?,
    @field:[Expose SerializedName("type")]
    val type: String?
)