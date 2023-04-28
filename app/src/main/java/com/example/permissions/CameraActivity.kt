package com.example.permissions

import android.hardware.Camera
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class CameraActivity : AppCompatActivity() {

    private lateinit var camera: Camera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Get the camera instance
        camera = Camera.open()
    }

    override fun onResume() {
        super.onResume()

        try {
            // Set the preview display to null, as we don't need it
            camera.setPreviewDisplay(null)
            // Start the preview
            camera.startPreview()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()

        // Stop the preview
        camera.stopPreview()
        // Release the camera resources
        camera.release()
    }
}