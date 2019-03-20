package com.orynastarkina.doittesttask.dataLayer.retrofit

import android.content.SharedPreferences
import com.orynastarkina.doittesttask.utils.TOKEN_KEY
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Oryna Starkina on 19.03.2019.
 */
class AuthInterceptor(private val preferences: SharedPreferences): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val headers = request.headers().newBuilder().add("Authorization",
            preferences.getString(TOKEN_KEY, "")!!).build()
        request = request.newBuilder().headers(headers).build()
        return chain.proceed(request)
    }
}