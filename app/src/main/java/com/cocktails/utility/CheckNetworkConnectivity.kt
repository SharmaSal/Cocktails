package com.cocktails.utility

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager


class CheckNetworkConnectivity {
    companion object {
        @SuppressLint("NewApi")
        fun checkIfNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return capabilities !== null
        }
    }

}