package com.example.permissions

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {

    companion object {
        const val PERMISSION_REQUEST_CAMERA = 0
    }

    private val btnCamera: Button by lazy { findViewById(R.id.btnOpenCamera) }
    private val layout: ConstraintLayout by lazy { findViewById(R.id.layoutCamera) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)



        btnCamera.setOnClickListener {
            if (checkSelfPermissionCompact(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
                startCamera()
                Toast.makeText(this, "Разрешение предоставлено", Toast.LENGTH_LONG).show()
            } else {
                requestCameraPermissions()
            }
        }
    }

    private fun requestCameraPermissions() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            Toast.makeText(this, "Разрешение не предоставлено", Toast.LENGTH_LONG).show()
            requestPermissionCompact(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        } else {
            Toast.makeText(this, "Разрешение не может быть запрошено", Toast.LENGTH_LONG).show()
            requestPermissionCompact(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        }
    }

    private fun startCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Разрешение предоставлено", Toast.LENGTH_LONG).show()
                startCamera()
            } else {
                Toast.makeText(this, "Разрешение не предоставлено", Toast.LENGTH_LONG).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}

fun AppCompatActivity.checkSelfPermissionCompact(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShownRequestPermissionRationaleCompact(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionCompact(
    permissionArray: Array <String>,
    requestCode: Int
) {
    ActivityCompat.requestPermissions(this, permissionArray, requestCode)
}