package com.example.pipdynamicfeature

import android.app.PictureInPictureParams
import android.app.PictureInPictureUiState
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dynamicfeaturesample.DynamicBaseActivity
import com.google.android.play.core.splitcompat.SplitCompat

@RequiresApi(Build.VERSION_CODES.O)
class PictureInPictureActivity : DynamicBaseActivity() {
    private lateinit var videoView: VideoView
    private lateinit var toolbar: Toolbar
    private lateinit var pip: Button
    private lateinit var recyclerView: RecyclerView
    private val pictureInPictureParamsBuilder = PictureInPictureParams.Builder()
    private val defaultVideo =
        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)
        initToolbar()
        initVideoView()
        initPipButton()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        val adapter = SampleItemAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.submitList(MutableList(10) {
            it
        })
    }

    private fun initPipButton() {
        pip = findViewById(R.id.pip)
        pip.setOnClickListener {
            pictureInPictureMode()
        }
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setActionBar(toolbar)
        toolbar.subtitle = "Picture in Picture"
    }

    private fun initVideoView() {
        videoView = findViewById(R.id.videoView)
        videoView.setVideoPath(defaultVideo)
        videoView.start()
    }

    private fun pictureInPictureMode() {
        val aspectRatio = Rational(videoView.width, videoView.height)
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build()
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build())
    }

    override fun onUserLeaveHint() {
        if (!isInPictureInPictureMode) {
            val aspectRatio = Rational(videoView.width, videoView.height)
            pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build()
            enterPictureInPictureMode(pictureInPictureParamsBuilder.build())
        }
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration?
    ) {
        SplitCompat.installActivity(this)
        if (isInPictureInPictureMode) {
            pip.visibility = View.GONE
            toolbar.visibility = View.GONE
        } else {
            pip.visibility = View.VISIBLE
            toolbar.visibility = View.VISIBLE
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        SplitCompat.installActivity(this)
        super.onConfigurationChanged(newConfig)
    }

    override fun onPictureInPictureUiStateChanged(pipState: PictureInPictureUiState) {
        SplitCompat.installActivity(this)
        super.onPictureInPictureUiStateChanged(pipState)
    }

    override fun onStop() {
        if (videoView.isPlaying) {
            videoView.stopPlayback()
        }
        super.onStop()
    }
}