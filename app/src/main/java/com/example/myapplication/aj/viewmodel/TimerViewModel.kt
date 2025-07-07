package com.example.myapplication.aj.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class TimerViewModel : ViewModel() {
    private lateinit var onTimeChangeListener: OnTimeChangeListener
    private var timer: Timer = Timer()
    private var currentSecond: MutableLiveData<Int>? = null

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    fun getCurrentSecond(): MutableLiveData<Int> {
        if (currentSecond == null) {
            currentSecond = MutableLiveData<Int>()
        }
        return currentSecond!!
    }

    fun startTiming() {
        timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                val updatedSecond = currentSecond?.value?.plus(1) ?: 1
                currentSecond?.postValue(updatedSecond)
                onTimeChangeListener?.onTimeChanged(updatedSecond)
            }
        }
        timer.schedule(task, 1000, 1000)
    }

    fun stopTiming() {
        timer.cancel()
    }

    fun setTimeChangeListener(onTimeChangeListener: OnTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener
    }

    interface OnTimeChangeListener {
        fun onTimeChanged(second: Int)
    }
}