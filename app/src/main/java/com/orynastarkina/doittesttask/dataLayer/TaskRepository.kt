package com.orynastarkina.doittesttask.dataLayer

import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class TaskRepository(val rest: IRest): ITaskRepository {
    override fun singUn(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        rest.singUp(email, password, callback)
    }

    override fun singIn(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        rest.singIn(email, password, callback)
    }
}