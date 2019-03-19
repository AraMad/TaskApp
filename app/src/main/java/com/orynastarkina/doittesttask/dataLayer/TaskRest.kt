package com.orynastarkina.doittesttask.dataLayer

import android.content.Context
import com.orynastarkina.doittesttask.BASE_URL
import com.orynastarkina.doittesttask.dataLayer.retrofit.Endpoints
import com.orynastarkina.doittesttask.dataLayer.retrofit.SuccessAuth
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class TaskRest(context: Context): IRest {

    private val endpoints: Endpoints
    private val networkManager: NetworkManager

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        endpoints = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

        networkManager = NetworkManager(context)
    }

    override fun singIn(email: String,
                        password: String,
                        callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {

        if (networkManager.isNetworkAvailable()){
            endpoints.singIn(email, password).enqueue(object : Callback<SuccessAuth>{

                override fun onFailure(call: Call<SuccessAuth>, t: Throwable) {
                    callback(null, t)
                }

                override fun onResponse(call: Call<SuccessAuth>, response: Response<SuccessAuth>) {
                    if (response.isSuccessful){
                        callback(response.body(), null)
                    } else {
                        // todo: pass correct error messages
                        callback(null, Throwable(response.errorBody().toString()))
                    }
                }

            })
        } else {
            callback(null, Throwable("No Internet connection"))
        }
    }

    override fun singUp(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        if (networkManager.isNetworkAvailable()){
            endpoints.singUp(email, password).enqueue(object : Callback<SuccessAuth>{

                override fun onFailure(call: Call<SuccessAuth>, t: Throwable) {
                    callback(null, t)
                }

                override fun onResponse(call: Call<SuccessAuth>, response: Response<SuccessAuth>) {
                    if (response.isSuccessful){
                        callback(response.body(), null)
                    } else {
                        callback(null, Throwable(response.errorBody().toString()))
                    }
                }

            })
        } else {
            callback(null, Throwable("No Internet connection"))
        }
    }

}