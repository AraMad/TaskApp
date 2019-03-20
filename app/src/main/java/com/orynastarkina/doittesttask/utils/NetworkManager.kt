package com.orynastarkina.doittesttask.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Oryna Starkina on 18.03.2019.
 */
class NetworkManager(context: Context) {

    private val connectivityService = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable(): Boolean = connectivityService.activeNetworkInfo != null &&
            (connectivityService.activeNetworkInfo.isConnected)
}