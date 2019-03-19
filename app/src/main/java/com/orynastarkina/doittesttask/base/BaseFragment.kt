package com.orynastarkina.doittesttask.base

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
abstract class BaseFragment<V : ViewModel, B : ViewDataBinding> : Fragment() {

    protected var TAG = BaseFragment::class.java.simpleName

    protected val NO_DATABINDING_VIEWMODEL_VARIABLE_ID = -1

    lateinit var viewModel: V

    protected lateinit var binding : B

    /**
     * Must return an instance of ViewModel : V for current Fragment
     */
    abstract fun obtainViewModel(): V

    @LayoutRes
    abstract fun getContentViewLayoutId(): Int

    abstract fun getTagName(): String

    /**
     * Provide a binding model variableId from BR generated class
     * or
     * NO_DATABINDING_VIEWMODEL_VARIABLE_ID if no variable must be set
     */
    abstract fun getBindingViewModelVariableId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
            inflater, getContentViewLayoutId(),
            container, false) as B
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        binding.setVariable(getBindingViewModelVariableId(), this.viewModel)
    }

    /**
     * Called immediately after getting instance of viewModel and after onActivityCreated()
     * by Android lifecycle
     */
    protected abstract fun onViewModelReady()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initClassTag()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        onViewModelReady()
    }

    private fun initClassTag() {
        TAG = getTagName()
    }

}