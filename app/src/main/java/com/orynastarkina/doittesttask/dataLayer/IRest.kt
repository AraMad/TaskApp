package com.orynastarkina.doittesttask.dataLayer

import android.provider.ContactsContract
import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
interface IRest {

    fun singIn(email: String, password: String,
               callback: (data: SuccessAuth?, err: Throwable?) -> Unit)

    fun singUp(email: String, password: String,
               callback: (data: SuccessAuth?, err: Throwable?) -> Unit)


}