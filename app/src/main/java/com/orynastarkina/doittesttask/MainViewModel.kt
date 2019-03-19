package com.orynastarkina.doittesttask

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.view.View
import com.orynastarkina.doittesttask.base.BaseViewModel
import com.orynastarkina.doittesttask.base.IRouter
import com.orynastarkina.doittesttask.dataLayer.TaskRepository

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class MainViewModel(repository: TaskRepository) : BaseViewModel<TaskRepository>(repository) {

    private val TAG = this.javaClass.simpleName

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val loginCheckbox = ObservableField<Boolean>()

    val fragmentRout = MutableLiveData<Event<IRouter.Fragments>>()

    fun onLoginClick(view: View) {

        // todo: check email with regex
        if (email.get()?.isNotEmpty() == true && password.get()?.isNotEmpty() == true) {
            if (loginCheckbox.get() == true) {

                repository.singIn(email.get()!!, password.get()!!) { success, error ->
                    if (success != null) {
                        fragmentRout.postValue(Event(IRouter.Fragments.TASK_LIST))
                    } else {
                        error?.let {
                            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                repository.singUn(email.get()!!, password.get()!!) { success, error ->
                    if (success != null) {
                        fragmentRout.postValue(Event(IRouter.Fragments.TASK_LIST))
                    } else {
                        error?.let {
                            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        } else {
            Snackbar.make(view, R.string.sing_in_login_empty_fields_error_text, Snackbar.LENGTH_LONG).show()
        }
    }
}