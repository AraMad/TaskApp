package com.orynastarkina.doittesttask.dataLayer.retrofit

/**
 * Created by Oryna Starkina on 18.03.2019.
 */

import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("fields")
    val fields: Fields? = null,
    @SerializedName("message")
    val message: String = ""
)

data class Fields(
    @SerializedName("email")
    val email: List<String> = listOf()
)

data class SuccessAuth(
    @SerializedName("token")
    val token: String = ""
)

data class Task(
    @SerializedName("task")
    val task: RegisteredTask = RegisteredTask()
)

data class RegisteredTask(
    @SerializedName("dueBy")
    val dueBy: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("priority")
    val priority: String = "",
    @SerializedName("title")
    val title: String = ""
)

data class TaskInfo(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("dueBy")
    val dueBy: String = "",
    @SerializedName("priority")
    val priority: String = ""
)

data class Meta(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("current")
    val current: Int = 0,
    @SerializedName("limit")
    val limit: Int = 0
)

data class TasksPage(
    @SerializedName("tasks")
    val tasks: List<RegisteredTask> = listOf(),
    @SerializedName("meta")
    val meta: Meta = Meta()
)