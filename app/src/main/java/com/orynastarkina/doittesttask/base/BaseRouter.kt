package com.orynastarkina.doittesttask.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
abstract class BaseRouter(activity: AppCompatActivity) : IRouter {

    protected var activityReference = WeakReference(activity)

    var fragmentRoute = LinkedList<IRouter.Fragments>()

    var activityRoute = LinkedList<IRouter.Activities>()

    /**
     * Attach Fragment to current Activity
     *
     * @param fragment: fragment to be attached
     * @param frameId: id of xml frame element to what fragment must be attached
     * @param tag: tag with what fragment wil be attached
     * @param data: fragments data
     */
    protected fun attachFragment(fragment: BaseFragment<*, *>,
                                 frameId: Int,
                                 isAddToBackStack: Boolean = true,
                                 isReplace: Boolean = true,
                                 tag: String? = null,
                                 data: Bundle? = null) {

        val transaction = activityReference.get()!!.supportFragmentManager.beginTransaction()
        if (data != null) {
            fragment.arguments = data
        }
        if (isReplace) {
            transaction.replace(frameId, fragment, tag ?: fragment.getTagName())
        } else {
            transaction.add(frameId, fragment, tag ?: fragment.getTagName())
        }
        if (isAddToBackStack) {
            transaction.addToBackStack(tag ?: fragment.getTagName())
        }
        transaction.commit()
    }

    protected fun findFragmentByTag(tag: String): Fragment? {
        val fragmentManager = activityReference.get()!!.supportFragmentManager
        return fragmentManager.findFragmentByTag(tag)
    }

    protected fun findFragmentById(containerId: Int): BaseFragment<*, *>? {
        return if (activityReference.get() != null && activityReference.get()!!.supportFragmentManager.findFragmentById(containerId) != null) {
            activityReference.get()!!.supportFragmentManager.findFragmentById(containerId) as BaseFragment<*, *>
        } else {
            null
        }
    }

    override fun getCurrentActivityRoute(): IRouter.Activities? {
        return if (activityRoute.isNotEmpty()) {
            activityRoute.last
        } else {
            null
        }
    }

    override fun getCurrentFragmentRoute(): IRouter.Fragments? {
        return if (fragmentRoute.isNotEmpty()) {
            fragmentRoute.last
        } else {
            null
        }
    }

    override fun moveToNextFragment(direction: IRouter.Fragments) {
        fragmentRoute.addLast(direction)
    }

    override fun moveToNextFragmentWithData(direction: IRouter.Fragments, bundle: Bundle) {
        fragmentRoute.addLast(direction)
    }

    override fun moveToNextFragmentWithResult(direction: IRouter.Fragments, requestCode: Int) {
        fragmentRoute.addLast(direction)
    }

    override fun moveToNextFragmentWithResult(direction: IRouter.Fragments, bundle: Bundle, requestCode: Int) {
        fragmentRoute.addLast(direction)
    }

    override fun moveToNextActivity(direction: IRouter.Activities) {
        activityRoute.addLast(direction)
    }

    override fun moveToNextActivityWithResult(direction: IRouter.Activities, requestCode: Int) {
        activityRoute.addLast(direction)
    }

    override fun moveBack() {
        if (activityReference.get()!!.supportFragmentManager?.backStackEntryCount == 0) {
            if (activityRoute.isNotEmpty()) {
                activityRoute.removeLast()
            }
            finish()
        } else {
            if (fragmentRoute.isNotEmpty()) {
                fragmentRoute.removeLast()
            }
            activityReference.get()!!.supportFragmentManager?.popBackStack()
        }
    }

    override fun finish() {
        activityReference.get()!!.finish()
    }

}