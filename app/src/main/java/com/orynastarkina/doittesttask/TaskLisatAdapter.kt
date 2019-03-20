package com.orynastarkina.doittesttask

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orynastarkina.doittesttask.dataLayer.retrofit.RegisteredTask
import com.orynastarkina.doittesttask.databinding.TaskItemBinding

/**
 * Created by Oryna Starkina on 19.03.2019.
 */

class TaskListAdapter(private val tasks: ArrayList<RegisteredTask>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.task_item, parent,
                false
            )
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }


    class ViewHolder(private val itemBinding: TaskItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(model: RegisteredTask) {
            itemBinding.model = model
            itemBinding.executePendingBindings()
        }
    }
}