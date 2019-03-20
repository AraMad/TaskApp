package com.orynastarkina.doittesttask.fragments

import android.arch.lifecycle.Observer
import android.support.design.widget.Snackbar
import com.orynastarkina.doittesttask.BR
import com.orynastarkina.doittesttask.MainViewModel
import com.orynastarkina.doittesttask.R
import com.orynastarkina.doittesttask.base.BaseActivity
import com.orynastarkina.doittesttask.base.BaseFragment
import com.orynastarkina.doittesttask.databinding.FragmentLoginBinding

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class LoginFragment : BaseFragment<MainViewModel, FragmentLoginBinding>() {

    override fun obtainViewModel() = (activity as BaseActivity<*, *>).viewModel as MainViewModel

    override fun getContentViewLayoutId() = R.layout.fragment_login

    override fun getTagName() = this.javaClass.simpleName

    override fun getBindingViewModelVariableId() = BR.viewModel

    override fun onViewModelReady() {
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
    }
}