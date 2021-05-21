package com.intellect.ello.android

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Applicate:Application() {
    override fun onCreate() {
        super.onCreate()

        val context: Activity ?= null
        val x = getData(context!!)
        if (x == false){
            startActivity(Intent(context, Login::class.java))
        }
    }

    fun getData(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}