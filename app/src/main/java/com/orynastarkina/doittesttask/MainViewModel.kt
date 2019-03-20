package com.orynastarkina.doittesttask

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import android.view.View
import com.orynastarkina.doittesttask.base.BaseViewModel
import com.orynastarkina.doittesttask.base.IRouter
import com.orynastarkina.doittesttask.dataLayer.TaskRepository
import com.orynastarkina.doittesttask.dataLayer.retrofit.RegisteredTask
import com.orynastarkina.doittesttask.utils.Event

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class MainViewModel(repository: TaskRepository) : BaseViewModel<TaskRepository>(repository) {

    private val TAG = this.javaClass.simpleName

    val fragmentRout = MutableLiveData<Event<IRouter.Fragments>>()
    val displayMessage = MutableLiveData<Event<String>>()

    // region login
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val loginCheckbox = ObservableField<Boolean>()

    fun onLoginClick(view: View) {

        if (email.get()?.isNotEmpty() == true && password.get()?.isNotEmpty() == true) {
            if (loginCheckbox.get() == true) {

                repository.singIn(email.get()!!, password.get()!!) { success, error ->
                    if (success != null) {
                        fragmentRout.postValue(Event(IRouter.Fragments.TASK_LIST))
                    } else {
                        error?.let {
                            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
                            displayMessage.postValue(Event(it.message.toString()))
                        }
                    }
                }
            } else {
                repository.singUp(email.get()!!, password.get()!!) { success, error ->
                    if (success != null) {
                        fragmentRout.postValue(Event(IRouter.Fragments.TASK_LIST))
                    } else {
                        error?.let {
                            displayMessage.postValue(Event(it.message.toString()))
                        }
                    }
                }
            }
        } else {
            Snackbar.make(view, R.string.sing_in_login_empty_fields_error_text, Snackbar.LENGTH_LONG).show()
        }
    }
    // endregion

    // region tasks screen
    var tasks = ArrayList<RegisteredTask>()

    val taskListChanged = MutableLiveData<Event<Unit>>()

    val isRefreshing = ObservableField<Boolean>()

    fun initTasksList() {
        if (tasks.isEmpty()) {
            repository.getTasks(1, "title asc") { data, error ->
                if (data != null) {
                    tasks = data.tasks as ArrayList<RegisteredTask>
                    taskListChanged.postValue(Event(Unit))
                } else {
                    tasks.add(RegisteredTask("08/05/19", "893749", "Height", "Test task"))
                    taskListChanged.postValue(Event(Unit))
                    error?.let {
                        displayMessage.postValue(Event(it.message.toString()))
                    }
                }
            }
        }
    }

    fun addTaskClick(view: View) {
        displayMessage.postValue(Event("add task"))
    }

    fun refreshTasks() {
        displayMessage.postValue(Event("refresh tasks"))
        tasks.add(RegisteredTask("08/05/19", "893749", "Height", "Test task" + tasks.size))
        taskListChanged.postValue(Event(Unit))
        isRefreshing.set(false)
        isRefreshing.notifyChange()
    }
    // endregion
}