package com.orynastarkina.doittesttask.fragments

import android.arch.lifecycle.Observer
import com.orynastarkina.doittesttask.BR
import com.orynastarkina.doittesttask.MainViewModel
import com.orynastarkina.doittesttask.R
import com.orynastarkina.doittesttask.base.BaseActivity
import com.orynastarkina.doittesttask.base.BaseFragment
import com.orynastarkina.doittesttask.databinding.FragmentTasksListBinding

/**
 * Created by Oryna Starkina on 19.03.2019.
 */
class TaskListFragment: BaseFragment<MainViewModel, FragmentTasksListBinding>() {

    override fun obtainViewModel() = (activity as BaseActivity<*, *>).obtainViewModel() as MainViewModel

    override fun getContentViewLayoutId() = R.layout.fragment_tasks_list

    override fun getTagName() = this.javaClass.simpleName

    override fun getBindingViewModelVariableId() = BR.viewModel

    override fun onViewModelReady() {
        viewModel.fragmentRout.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { direction ->
                (activity as BaseActivity<*, *>).router.moveToNextFragment(direction)
            }
        })
    }
}