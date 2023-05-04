package com.example.permissions

import android.hardware.Camera
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class CameraActivity : AppCompatActivity() {

    private lateinit var camera: Camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        camera = Camera.open()
    }

    override fun onResume() {
        super.onResume()

        try {
            camera.setPreviewDisplay(null)
            camera.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()

        camera.stopPreview()
        camera.release()
    }
}