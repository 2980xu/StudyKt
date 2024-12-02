package com.example.myapplication.jk

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService

class MyService : LifecycleService() {
    companion object {
        const val TAG = "MyServiceObserver"
    }
    private lateinit var myServiceObserver: MyServiceObserver

    init {
        Log.d(TAG, "Service initialized")
        myServiceObserver = MyServiceObserver()
        lifecycle.addObserver(myServiceObserver)
    }

    @Suppress("DEPRECATION")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand called")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy called")
        super.onDestroy()
    }

    @Suppress("DEPRECATION")
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG, "onStart called")
    }
}