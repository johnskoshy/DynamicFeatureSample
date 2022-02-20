package com.example.dynamicfeaturesample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class HomeActivity : Activity() {
    private lateinit var manager: SplitInstallManager
    private lateinit var progress: ProgressBar
    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                val progressValue = ((state.bytesDownloaded()
                    .toFloat() / state.totalBytesToDownload()) * 100).toInt()
                progress.progress = progressValue
            }
            SplitInstallSessionStatus.INSTALLED -> {
                launchDynamicFeature()
            }

            SplitInstallSessionStatus.INSTALLING -> progress.progress = 100
            SplitInstallSessionStatus.FAILED -> {
                Toast.makeText(this, "Install failed", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        manager = SplitInstallManagerFactory.create(this)
        findViewById<Button>(R.id.textButton).setOnClickListener {
            launchDynamicFeature()
        }
        progress = findViewById(R.id.progress_horizontal)
    }

    override fun onStart() {
        super.onStart()
        manager.registerListener(listener)
    }

    override fun onStop() {
        super.onStop()
        manager.unregisterListener(listener)
    }

    private fun launchDynamicFeature() {
        if (manager.installedModules.contains(getString(R.string.title_dynamicfeature))) {
            val intent = Intent()
            intent.setClassName(
                packageName,
                "com.example.dynamicfeaturesample.dynamicfeature.DynamicFeatureActivity"
            )
            startActivity(intent)
            return
        }
        val request =
            SplitInstallRequest.newBuilder().addModule(getString(R.string.title_dynamicfeature))
                .build()
        manager.startInstall(request)
    }
}