package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.jk.MyLocationListener
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.jk.MyService

class MainActivity : FragmentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    lateinit var locationListener: MyLocationListener
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
        initLocation()

    }

    private fun initView() {
        Log.d(TAG, "initView: ")
        binding.btnStartService.setOnClickListener {
            Log.d("MyServiceObserver", "btnStartService: ")
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        binding.btnStopService.setOnClickListener {
            Log.d("MyServiceObserver", "btnStopService: ")
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
    }

    private fun initLocation() {
        Log.d(TAG, "initLocation: ")
        // 初始化 MyLocationListener
        locationListener = MyLocationListener(this, object : MyLocationListener.OnLocationListener {
            override fun onChanged(tag: String) {
                // 处理位置变化
                Log.d(MyLocationListener.TAG, "Location event: $tag")
            }
        })

        // 将 MyLocationListener 添加到 Lifecycle
        lifecycle.addObserver(locationListener)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}