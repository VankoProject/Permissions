package com.kliachenko.permissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kliachenko.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //for use Activity result APi we have to create special launcher
    private val feature3PermissionsRequestLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::onGotPermissionsResultForFeature3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.feature1Button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                onCameraPermissionGranted()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    RQ_PERMISSION_FOR_FEATURE_1_CODE
                )
            }
        }

        binding.feature2Button.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.RECORD_AUDIO
                ),
                RQ_PERMISSION_FOR_FEATURE_2_CODE
            )
        }

        binding.feature3Button.setOnClickListener {
            feature3PermissionsRequestLauncher.launch(arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.RECORD_AUDIO
            ))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RQ_PERMISSION_FOR_FEATURE_1_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onCameraPermissionGranted()
                } else {
                    if (!shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
                        // show dialog with explanation here
                        askUserForOpeningAppSettings()
                    } else {
                        // oops, can't do anything
                        Toast.makeText(
                            this,
                            "Permission denied",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            RQ_PERMISSION_FOR_FEATURE_2_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Toast.makeText(
                        this,
                        "Location & record audio permissions granted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    //Code for Activity Result API
    private fun onGotPermissionsResultForFeature3(grantResults: Map<String, Boolean>) {
        if (grantResults.entries.all { it.value}) {
            Toast.makeText(
                this,
                "Location & record audio permissions granted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun askUserForOpeningAppSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        )
        if (packageManager.resolveActivity(
                appSettingsIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            ) == null
        ) {
            Toast.makeText(this, "Permissions are denied forever", Toast.LENGTH_SHORT).show()
        } else {
            AlertDialog.Builder(this)
                .setTitle("Permissions denied")
                .setMessage(
                    "You have denied permissions forever. " +
                            "You can change your decision in app settings.\n\n" +
                            "Whould you like to open app settings?"
                )
                .setPositiveButton("Open") { _, _ ->
                    startActivity(appSettingsIntent)
                }
                .create()
                .show()
        }
    }

    private fun onCameraPermissionGranted() {
        Toast.makeText(this, "camera permission is granted", Toast.LENGTH_SHORT).show()
    }

    private companion object {
        const val RQ_PERMISSION_FOR_FEATURE_1_CODE = 1
        const val RQ_PERMISSION_FOR_FEATURE_2_CODE = 2
    }
}