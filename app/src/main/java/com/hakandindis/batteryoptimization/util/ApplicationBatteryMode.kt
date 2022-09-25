package com.hakandindis.batteryoptimization.util

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.PowerManager
import androidx.annotation.RequiresApi

class ApplicationBatteryMode(private val context:Context) {

    @RequiresApi(Build.VERSION_CODES.P)
    fun isAppRestrictedMode():Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return manager.isBackgroundRestricted
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isAppNotRestrictedMode():Boolean {
        val manager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return manager.isIgnoringBatteryOptimizations(context.packageName)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun isAppOptimizedMode():Boolean {
        return !isAppRestrictedMode() && isAppNotRestrictedMode()
    }

}