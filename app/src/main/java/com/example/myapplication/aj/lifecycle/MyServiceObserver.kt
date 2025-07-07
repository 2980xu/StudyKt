package com.example.myapplication.aj.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyServiceObserver : LifecycleObserver {
    companion object {
        const val TAG = "MyServiceObserver"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startServiceLocation() {
        Log.d(TAG, "startServiceLocation: ")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopServiceLocation() {
        Log.d(TAG, "stopServiceLocation: ")
    }
}