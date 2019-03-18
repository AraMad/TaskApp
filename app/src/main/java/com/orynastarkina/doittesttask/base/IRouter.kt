package com.orynastarkina.doittesttask.base

import android.os.Bundle

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
interface IRouter {

    /** For saving fragments path call super() of this method */
    fun moveToNextActivity(direction: Activities) {
        throw Exception("moveToNextScreen(direction: Activities) from IRouter : not implemented")
    }

    /** For saving fragments path call super() of this method */
    fun moveToNextActivityWithResult(direction: Activities, requestCode: Int) {
        throw Exception("moveToNextActivityWithResult(direction: Activities, requestCode: Int) from IRouter : not implemented")
    }

    /** For saving fragments path call super() of this method */
    fun moveToNextFragment(direction: Fragments) {
        throw Exception("moveToNextFragment(direction: Fragments) from IRouter : not implemented")
    }

    /** For saving fragments path call super() of this method */
    fun moveToNextFragmentWithData(direction: Fragments, bundle: Bundle) {
        throw Exception("moveToNextFragmentWithData(direction: Fragments, bundle: Bundle) from IRouter : not implemented")
    }

    /** For saving fragments path call super() of this method */
    fun moveToNextFragmentWithResult(direction: Fragments, requestCode: Int) {
        throw Exception("moveToNextFragmentWithResult(direction: Fragments, requestCode: Int) from IRouter : not implemented")
    }

    /** For saving fragments path call super() of this method */
    fun moveToNextFragmentWithResult(direction: Fragments, bundle: Bundle, requestCode: Int) {
        throw Exception("moveToNextFragmentWithResult(direction: Fragments, requestCode: Int) from IRouter : not implemented")
    }

    fun moveBack() {
        throw Exception("moveBack() from IRouter : not implemented")
    }

    fun finish() {
        throw Exception("finish() from IRouter : not implemented")
    }

    fun getCurrentFragmentRoute(): IRouter.Fragments? {
        throw Exception("getCurrentFragmentRoute() : not implemented")
    }

    fun getCurrentActivityRoute(): IRouter.Activities? {
        throw Exception("getCurrentActivityRoute() : not implemented")
    }

    // todo: add constants for new Activities
    /**
     * Enum of all Activities(screens) in app
     */
    enum class Activities {

    }

    // todo: add constants for new Fragments
    /**
     * Enum of all Fragments in app
     */
    enum class Fragments {

    }
}