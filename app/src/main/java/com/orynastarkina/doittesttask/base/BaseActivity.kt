package com.orynastarkina.doittesttask.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.orynastarkina.doittesttask.utils.showPreLoader

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
abstract class BaseActivity<V : ViewModel, B : ViewDataBinding> : AppCompatActivity() {

    protected var TAG = BaseFragment::class.java.simpleName

    protected val NO_DATABINDING_VARIABLE_ID = -1

    lateinit var viewModel: V
    lateinit var router: IRouter

    var preLoader: AlertDialog? = null

    protected lateinit var binding : B

    /**
     * Provide Router object for current Activity
     */
    abstract fun obtainRouter(): IRouter

    /**
     * Must return an instance of ViewModel : V for current Activity
     */
    abstract fun obtainViewModel(): V

    abstract fun getTagName(): String

    @LayoutRes
    abstract fun getContentViewLayoutId(): Int

    /**
     * Provide a binding model variableId from BR generated class
     * or
     * NO_DATABINDING_VARIABLE_ID if no variable must be set
     */
    abstract fun getBindingViewModelVariableId(): Int

    /**
     * Called immediately after getting instance of viewModel and after onCreate() by Android
     * lifecycle
     */
    protected abstract fun onViewModelReady()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initClassTag()

        initViewModel()

        initBinding()
        setContentView(binding.root)

        initRouter()
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(layoutInflater, getContentViewLayoutId(),
            null, false) as B
        binding.setVariable(getBindingViewModelVariableId(), this.viewModel)
    }

    private fun initRouter() {
        router = obtainRouter()
    }

    private fun initViewModel() {
        viewModel = obtainViewModel()
        onViewModelReady()
    }

    private fun initClassTag() {
        TAG = getTagName()
    }

    override fun onResume() {
        super.onResume()
        (viewModel as BaseViewModel<*>).isLoading.observe(this, loadingObserver)
    }


    override fun onPause() {
        super.onPause()
        preLoader?.dismiss()
        (viewModel as BaseViewModel<*>).isLoading.removeObserver(loadingObserver)
    }

    private var loadingObserver = Observer<Boolean> {
        preLoader = if (it!!) {
            showPreLoader(this, null)
        } else {
            preLoader?.dismiss()
            null
        }
    }

}