package com.orynastarkina.doittesttask.utils

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout

/**
 * Created by Oryna Starkina on 20.03.2019.
 */

@BindingAdapter("refreshing")
fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, isRefreshing: Boolean){
    swipeRefreshLayout.isRefreshing = isRefreshing
}