package com.orynastarkina.doittesttask

import com.orynastarkina.doittesttask.base.BaseRouter
import com.orynastarkina.doittesttask.base.IRouter
import com.orynastarkina.doittesttask.fragments.LoginFragment
import com.orynastarkina.doittesttask.fragments.TaskListFragment

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class MainRouter(activity: MainActivity): BaseRouter(activity) {
    override fun moveToNextFragment(direction: IRouter.Fragments) {
        super.moveToNextFragment(direction)

        when (direction){
            IRouter.Fragments.LOG_IN -> {
                attachFragment(LoginFragment(), R.id.container, tag = direction.toString())
            }
            IRouter.Fragments.TASK_LIST -> {
                attachFragment(TaskListFragment(), R.id.container, tag = direction.toString())
            }
            else -> {
                throw Exception("${this.javaClass.simpleName}: wrong direction - $direction")
            }
        }
    }
}