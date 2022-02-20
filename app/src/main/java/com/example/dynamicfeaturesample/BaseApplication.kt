package com.example.dynamicfeaturesample

import android.util.Log
import com.google.android.play.core.splitcompat.SplitCompatApplication

val TAG: String = BaseApplication::class.java.simpleName

class BaseApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }
}