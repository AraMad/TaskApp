package com.orynastarkina.doittesttask.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class BaseViewModel(protected val repository: IRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>().apply {
        postValue(false)
    }

}
