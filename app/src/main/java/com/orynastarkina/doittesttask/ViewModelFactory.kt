package com.orynastarkina.doittesttask

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.orynastarkina.doittesttask.dataLayer.TaskRepository
import com.orynastarkina.doittesttask.dataLayer.TaskRest
import com.orynastarkina.doittesttask.utils.REST_PREFS_NAME

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class ViewModelFactory private
constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory? {

            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(
                    TaskRepository(
                        TaskRest(
                            mApplication.applicationContext,
                            mApplication.getSharedPreferences(REST_PREFS_NAME, Context.MODE_PRIVATE)
                        )
                    )
                ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}