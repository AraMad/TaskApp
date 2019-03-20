package com.orynastarkina.doittesttask.dataLayer

import com.orynastarkina.doittesttask.dataLayer.interfaces.ITaskRepository
import com.orynastarkina.doittesttask.dataLayer.interfaces.ITaskRest
import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth
import com.orynastarkina.doittesttask.dataLayer.retrofit.TasksPage

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class TaskRepository(private val rest: ITaskRest): ITaskRepository {

    override fun singUp(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        rest.singUp(email, password, callback)
    }

    override fun singIn(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        rest.singIn(email, password, callback)
    }

    override fun getTasks(page: Int, sort: String, callback: (data: TasksPage?, err: Throwable?) -> Unit) {
        rest.getTasks(page, sort, callback)
    }
}