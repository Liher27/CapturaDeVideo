package com.example.videocapturer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private val REQUEST_CODE_RECORD_VIDEO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView = findViewById(R.id.videoView)
        findViewById<Button>(R.id.idBtnRecordVideo).setOnClickListener {
            val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            if (videoIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(videoIntent, REQUEST_CODE_RECORD_VIDEO)
            } else {
                Toast.makeText(this, "No camera app found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_RECORD_VIDEO && resultCode == RESULT_OK) {
            val selectedVideo: Uri? = data?.data
            if (selectedVideo != null) {
                videoView.setVideoURI(selectedVideo)
                videoView.start()
            } else {
                Toast.makeText(this, "Video recording canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }
}