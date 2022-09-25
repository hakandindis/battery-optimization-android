package com.hakandindis.batteryoptimization

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import com.hakandindis.batteryoptimization.databinding.ActivityMainBinding
import com.hakandindis.batteryoptimization.util.ApplicationBatteryMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var batteryMode:ApplicationBatteryMode

    @SuppressLint("BatteryLife")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        batteryMode = ApplicationBatteryMode(this@MainActivity)

        binding.activityManagerButton.setOnClickListener {
            if(batteryMode.isAppRestrictedMode()) {
                try {
                    val intent = Intent()
                    intent.action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                    intent.data = Uri.parse("package:$packageName")
                    startActivity(intent)
                }catch (e:Exception){
                    print(e.toString())
                    Log.d("HAKANNN",e.toString())
                    val intent = Intent()
                    intent.action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
                    startActivity(intent)
                }
            }
        }

        binding.optimizeButton.setOnClickListener {

            try {
              val intent = Intent()
              intent.setClassName("com.android.settings","com.android.settings.SubSettings")
              intent.putExtra("package_name",packageName)
              startActivity(intent)
            } catch (e:Exception) {
                Log.d("HAKANNN",e.toString())
                val intent = Intent()
                intent.action = Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
                startActivity(intent)
            }
        }
    }
}