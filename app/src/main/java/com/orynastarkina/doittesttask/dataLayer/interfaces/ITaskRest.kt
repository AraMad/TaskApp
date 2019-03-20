package com.orynastarkina.doittesttask.dataLayer.interfaces

import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth
import com.orynastarkina.doittesttask.dataLayer.retrofit.TasksPage

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
interface ITaskRest {

    fun singIn(
        email: String, password: String,
        callback: (data: SuccessAuth?, err: Throwable?) -> Unit
    )

    fun singUp(
        email: String, password: String,
        callback: (data: SuccessAuth?, err: Throwable?) -> Unit
    )


    fun getTasks(
        page: Int, sort: String,
        callback: (data: TasksPage?, err: Throwable?) -> Unit
    )
}