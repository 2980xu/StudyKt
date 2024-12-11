package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationChannel.DEFAULT_CHANNEL_ID
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.jk.MyLocationListener
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.jk.MyService

class MainActivity : FragmentActivity() {
    companion object {
        const val TAG = "MainActivity"
        private const val NOTIFICATION_ID = 1
        private const val DEFAULT_CHANNEL_ID = "my_notification_channel"
        private const val REQUEST_CODE = 12345 //
    }

    lateinit var locationListener: MyLocationListener
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
        }
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

        binding.btnSendNotification.setOnClickListener {
            sendNotification()
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

    @SuppressLint("MissingPermission")
    private fun sendNotification() {
        Log.d(TAG, "sendNotification: ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(DEFAULT_CHANNEL_ID, "ChannelName", importance)
            channel.description = "description"
            val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("DeepLinkDemo")
            .setContentText("Hello World")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(getPendingIntent())
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    private fun getPendingIntent(): PendingIntent? {
        Log.d(TAG, "getPendingIntent: ")
        val bundle = Bundle()
        bundle.putString("params", "Params_HelloMichael")
        return Navigation.findNavController(this, R.id.nav_host_fragment)
            .createDeepLink()
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.deepLinkSettings)
            .setArguments(bundle)
            .createPendingIntent(
            )
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