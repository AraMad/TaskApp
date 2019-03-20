package com.orynastarkina.doittesttask

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.annotation.StringRes
import android.view.Menu
import android.view.View
import com.orynastarkina.doittesttask.base.BaseActivity
import com.orynastarkina.doittesttask.base.IRouter
import com.orynastarkina.doittesttask.databinding.ActivityMainBinding
import com.orynastarkina.doittesttask.utils.MENU_NOT_SET
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getTagName() = this.javaClass.simpleName

    private var toolBarMenu = MENU_NOT_SET

    override fun obtainRouter() = MainRouter(this)

    override fun obtainViewModel() = ViewModelProviders.of(
        this, ViewModelFactory.getInstance(this.application)
    ).get(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            router.moveToNextFragment(IRouter.Fragments.LOG_IN)
        }
    }

    override fun getContentViewLayoutId() = R.layout.activity_main

    override fun getBindingViewModelVariableId() = NO_DATABINDING_VARIABLE_ID

    override fun onViewModelReady() {}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        if (toolBarMenu != MENU_NOT_SET) {
            menuInflater.inflate(toolBarMenu, menu)
        }

        return true
    }

    fun setupToolbar(
        @MenuRes menu: Int,
        @StringRes title: Int,
        isBakButtonEnabled: Boolean
    ) {
        toolBarMenu = menu
        invalidateOptionsMenu()
        toolbar?.visibility = View.VISIBLE
        toolbar_title.text = getString(title)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(isBakButtonEnabled)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onBackPressed() {
        router.finish()
    }
}
