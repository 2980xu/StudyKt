package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.myapplication.viewmodel.TimerViewModel

class SecondActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SecondActivity"
    }

    private lateinit var binding: ActivitySecondBinding
    private lateinit var timerViewModel:TimerViewModel
    private lateinit var currentSecond : MutableLiveData<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initTimerViewModel()
    }

    private fun initView() {
        Log.d(TAG, "initView: ")
        binding.tvStart.setOnClickListener {
            timerViewModel.startTiming()
        }

        binding.tvStop.setOnClickListener {
            timerViewModel.stopTiming()
        }

        binding.tvReset.setOnClickListener {
            currentSecond.postValue(0)
        }
    }

    private fun initTimerViewModel() {
        Log.d(TAG, "initTimerViewModel: ")
        timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        timerViewModel.setTimeChangeListener(object : TimerViewModel.OnTimeChangeListener {
            override fun onTimeChanged(second: Int) {
                runOnUiThread(object : Runnable {
                    override fun run() {
//                        binding.tvHello.setText("$second")
                    }
                })
            }
        })

        currentSecond = timerViewModel.getCurrentSecond()
        currentSecond.observeForever {
            runOnUiThread(object : Runnable {
                override fun run() {
                        binding.tvTime.setText("$it")
                }
            })
        }
    }
}