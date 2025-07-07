package com.example.myapplication

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.myapplication.aj.lifecycle.ApplicationObserver

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}