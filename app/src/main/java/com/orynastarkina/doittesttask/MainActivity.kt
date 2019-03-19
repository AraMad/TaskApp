package com.orynastarkina.doittesttask

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.orynastarkina.doittesttask.base.BaseActivity
import com.orynastarkina.doittesttask.base.IRouter
import com.orynastarkina.doittesttask.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getTagName() = this.javaClass.simpleName


    override fun obtainRouter() = MainRouter(this)

    override fun obtainViewModel() = ViewModelProviders.of(
        this, ViewModelFactory
            .getInstance(this.application)
    ).get(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (supportFragmentManager.findFragmentById(R.id.container) == null) {
            router.moveToNextFragment(IRouter.Fragments.LOG_IN)
        }
    }

    override fun getContentViewLayoutId() = R.layout.activity_main

    override fun getBindingViewModelVariableId() = NO_DATABINDING_VARIABLE_ID

    override fun onViewModelReady() {

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
