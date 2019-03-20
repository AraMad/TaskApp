package com.orynastarkina.doittesttask.dataLayer

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.orynastarkina.doittesttask.R
import com.orynastarkina.doittesttask.dataLayer.interfaces.ITaskRest
import com.orynastarkina.doittesttask.dataLayer.retrofit.*
import com.orynastarkina.doittesttask.utils.BASE_URL
import com.orynastarkina.doittesttask.utils.NetworkManager
import com.orynastarkina.doittesttask.utils.TOKEN_KEY
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class TaskRest(context: Context, private val prefs: SharedPreferences) : ITaskRest {

    private val endpoints: Endpoints
    private val networkManager: NetworkManager

    private val NO_INTERNET_CONNECTION_STRING = context.getString(R.string.no_internet_connection)

    init {
        //Add the interceptor to the client builder.
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(prefs))
            .build()

        endpoints = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Endpoints::class.java)

        networkManager = NetworkManager(context)
    }

    override fun singIn(
        email: String,
        password: String,
        callback: (data: SuccessAuth?, err: Throwable?) -> Unit
    ) {

        if (networkManager.isNetworkAvailable()) {
            endpoints.singIn(email, password).enqueue(object : Callback<SuccessAuth> {

                override fun onFailure(call: Call<SuccessAuth>, t: Throwable) {
                    callback(null, t)
                }

                override fun onResponse(call: Call<SuccessAuth>, response: Response<SuccessAuth>) {
                    if (response.isSuccessful) {
                        prefs.edit().putString(
                            TOKEN_KEY,
                            response.body()?.token
                        ).apply()
                        callback(response.body(), null)
                    } else {
                        val errorModel = Gson().fromJson(response.errorBody()?.string(), Error::class.java)
                        callback(null, Throwable(errorModel?.fields?.email?.first() ?: errorModel.message))
                    }
                }

            })
        } else {
            callback(null, Throwable(NO_INTERNET_CONNECTION_STRING))
        }
    }

    override fun singUp(email: String, password: String, callback: (data: SuccessAuth?, err: Throwable?) -> Unit) {
        if (networkManager.isNetworkAvailable()) {
            endpoints.singUp(email, password).enqueue(object : Callback<SuccessAuth> {

                override fun onFailure(call: Call<SuccessAuth>, t: Throwable) {
                    callback(null, t)
                }

                override fun onResponse(call: Call<SuccessAuth>, response: Response<SuccessAuth>) {
                    if (response.isSuccessful) {
                        prefs.edit().putString(
                            TOKEN_KEY,
                            response.body()?.token
                        ).apply()
                        callback(response.body(), null)
                    } else {
                        val errorModel = Gson().fromJson(response.errorBody()?.string(), Error::class.java)
                        callback(null, Throwable(errorModel?.fields?.email?.first() ?: errorModel.message))
                    }
                }

            })
        } else {
            callback(null, Throwable(NO_INTERNET_CONNECTION_STRING))
        }
    }

    override fun getTasks(page: Int, sort: String, callback: (data: TasksPage?, err: Throwable?) -> Unit) {
        if (networkManager.isNetworkAvailable()) {
            endpoints.getTasks(page, sort).enqueue(object : Callback<TasksPage> {

                override fun onFailure(call: Call<TasksPage>, t: Throwable) {
                    callback(null, t)
                }

                override fun onResponse(call: Call<TasksPage>, response: Response<TasksPage>) {
                    if (response.isSuccessful) {
                        callback(response.body(), null)
                    } else {
                        callback(null, Throwable(response.message()))
                    }
                }

            })
        } else {
            callback(null, Throwable(NO_INTERNET_CONNECTION_STRING))
        }
    }

}