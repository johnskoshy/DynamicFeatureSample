package com.example.dynamicfeaturesample.dynamicfeature

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.ColorRes
import com.example.dynamicfeaturesample.DynamicBaseActivity
import com.google.android.play.core.splitcompat.SplitCompat

class DynamicFeatureActivity : DynamicBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.DynamicActivityTheme)
        setContentView(R.layout.activity_dynamic_feature)
        val color = getAttributeColor(this, R.attr.dynamicTextColor)
        Toast.makeText(this, "Color id from style: $color", Toast.LENGTH_SHORT).show()
    }

    @ColorRes
    fun getAttributeColor(context: Context, attrRes: Int): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attrRes, typedValue, true)
        return typedValue.resourceId
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        installResourcesAndTheme()
        super.onConfigurationChanged(newConfig)
    }

    private fun installResourcesAndTheme() {
        SplitCompat.installActivity(this)
        val theme = super.getTheme()
        theme.applyStyle(R.style.DynamicActivityTheme, true)
    }
}