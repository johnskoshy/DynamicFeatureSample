package com.example.dynamicfeaturesample

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import com.google.android.play.core.splitcompat.SplitCompat

open class DynamicBaseActivity : Activity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        SplitCompat.installActivity(this)
        super.onConfigurationChanged(newConfig)
    }
}