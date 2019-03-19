package com.orynastarkina.doittesttask.dataLayer.retrofit

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
interface Endpoints {

    @FormUrlEncoded
    @POST("auth")
    fun singIn(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SuccessAuth>

    @FormUrlEncoded
    @POST("users")
    fun singUp(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<SuccessAuth>

    @GET("tasks")
    fun getTasks(
        @Query("page") page: Int,
        @Query("sort") sort: String
    )

    @PUT("tasks/{task}")
    fun editTask(@Path("task") taskId: Int, @Body taskInfo: TaskInfo): RegisteredTask

    @DELETE("tasks/{task}")
    fun deleteTask(@Path("task") taskId: Int)
}