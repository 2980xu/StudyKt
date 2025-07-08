package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.aj.roomdb.MyDataBase
import com.example.myapplication.aj.roomdb.StudentRoom
import com.example.myapplication.databinding.ActivitySecondBinding
import com.example.myapplication.aj.viewmodel.TimerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SecondActivity : AppCompatActivity() {
    companion object {
        const val TAG = "SecondActivity"
    }

    private lateinit var binding: ActivitySecondBinding
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var currentSecond: MutableLiveData<Int>

    private lateinit var studentRoom: StudentRoom
    private var id: Int = 134

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
//            timerViewModel.startTiming()
            addStudent()
        }

        binding.tvStop.setOnClickListener {
//            timerViewModel.stopTiming()
            updateStudent()
        }

        binding.tvReset.setOnClickListener {
//            currentSecond.postValue(0)
            getStudent()
        }

        binding.tvDelete.setOnClickListener{
            deleteStudent()
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

    private fun addStudent() {
        CoroutineScope(Dispatchers.IO).launch {
            var studentRoom = StudentRoom("王小训", "22")
            MyDataBase.getDataBase(this@SecondActivity).studentDao().insertStudent(studentRoom)
        }
    }

    private fun deleteStudent() {
        Log.d(TAG, "deleteStudent: ")
        var studentRoom = StudentRoom(185,"王小训", "22")
        CoroutineScope(Dispatchers.IO).launch {
            MyDataBase.getDataBase(this@SecondActivity).studentDao().deleteStudent(studentRoom)
        }
    }

    private fun updateStudent() {
        CoroutineScope(Dispatchers.IO).launch {
            var studentRoom = MyDataBase.getDataBase(this@SecondActivity).studentDao().getStudentById(183)
            studentRoom.name = "wangyiyi"
            studentRoom.age = "0.5"
            MyDataBase.getDataBase(this@SecondActivity).studentDao().updateStudent(studentRoom)
        }
    }

    private fun getStudent() {
        Log.d(TAG, "getStudent: ")
        CoroutineScope(Dispatchers.IO).launch {
            var studentList = MyDataBase.getDataBase(this@SecondActivity).studentDao().getStudentList()
            for (studentRoom in  studentList) {
                Log.d(TAG, "getStudent id: ${studentRoom.id} name: ${studentRoom.name} age: ${studentRoom.age}")
            }
        }

    }
}