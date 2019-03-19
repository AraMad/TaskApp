package com.orynastarkina.doittesttask

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.orynastarkina.doittesttask.base.IRepository
import com.orynastarkina.doittesttask.dataLayer.NetworkManager
import com.orynastarkina.doittesttask.dataLayer.TaskRepository
import com.orynastarkina.doittesttask.dataLayer.TaskRest

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
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(TaskRepository(TaskRest(mApplication.applicationContext))) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}