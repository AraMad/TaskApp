package com.orynastarkina.doittesttask.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.orynastarkina.doittesttask.*
import com.orynastarkina.doittesttask.base.BaseActivity
import com.orynastarkina.doittesttask.base.BaseFragment
import com.orynastarkina.doittesttask.databinding.FragmentTasksListBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_tasks_list.*

/**
 * Created by Oryna Starkina on 19.03.2019.
 */
class TaskListFragment: BaseFragment<MainViewModel, FragmentTasksListBinding>() {


    private lateinit var taskAdapter: TaskListAdapter

    override fun obtainViewModel() = (activity as BaseActivity<*, *>).obtainViewModel() as MainViewModel

    override fun getContentViewLayoutId() = R.layout.fragment_tasks_list

    override fun getTagName() = this.javaClass.simpleName

    override fun getBindingViewModelVariableId() = BR.viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        (activity as MainActivity).setupToolbar(R.menu.menu_main,
            R.string.tasks_list_toolbar_title,
            false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.name -> true
            R.id.priority -> true
            R.id.date -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tasks.layoutManager = LinearLayoutManager(activity)
        tasks.setHasFixedSize(true)
    }

    override fun onViewModelReady() {

        taskAdapter = TaskListAdapter(viewModel.tasks)
        tasks.adapter = taskAdapter

        viewModel.fragmentRout.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { direction ->
                (activity as BaseActivity<*, *>).router.moveToNextFragment(direction)
            }
        })

        viewModel.displayMessage.observe(this, Observer {
            it?.getContentIfNotHandled()?.let { message ->
                Snackbar.make(this.view!!, message, Snackbar.LENGTH_LONG).show()
            }
        })

        viewModel.taskListChanged.observe(this, Observer {
            it?.getContentIfNotHandled()?.let {
                taskAdapter.notifyDataSetChanged()
            }
        })
        viewModel.initTasksList()

        swipeContainer.setOnRefreshListener(viewModel::refreshTasks)
    }


}