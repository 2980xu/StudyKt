package com.example.myapplication.jk

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationListener(private val mContext: Context, private val mListener: OnLocationListener) : LifecycleObserver {

    companion object {
        const val TAG = "MyLocationListener"
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun startGetLocation() {
        Log.d(TAG, "startGetLocation: ")
        mListener.onChanged("startGetLocation")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun stopGetLocation() {
        Log.d(TAG, "stopGetLocation: ")
        mListener.onChanged("stopGetLocation")
    }

    interface OnLocationListener {
        fun onChanged(tag: String)
    }
}