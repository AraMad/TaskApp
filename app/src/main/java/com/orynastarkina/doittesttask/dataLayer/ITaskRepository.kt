package com.orynastarkina.doittesttask.dataLayer

import com.orynastarkina.doittesttask.base.IRepository
import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
interface ITaskRepository: IRepository {

    fun singIn(email: String,
               password: String,
               callback: (data: SuccessAuth?, err: Throwable?) -> Unit)

    fun singUn(email: String,
               password: String,
               callback: (data: SuccessAuth?, err: Throwable?) -> Unit)
}