package com.wifiscanner.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class WifiPermissionManager {
    companion object {
        // Constant for permission request code
        const val WIFI_PERMISSION_REQUEST_CODE = 100

        // Required permissions for WiFi scanning on Android 10+
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
        )

        /**
         * Checks if all required WiFi scanning permissions are granted
         * @param context Context of the application
         * @return Boolean indicating if all permissions are granted
         */
        fun hasWifiPermissions(context: Context): Boolean {
            return REQUIRED_PERMISSIONS.all { permission ->
                ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) == PackageManager.PERMISSION_GRANTED
            }
        }

        /**
         * Requests WiFi scanning permissions from the user
         * @param activity Activity requesting the permissions
         */
        fun requestWifiPermissions(activity: Activity) {
            ActivityCompat.requestPermissions(
                activity,
                REQUIRED_PERMISSIONS,
                WIFI_PERMISSION_REQUEST_CODE
            )
        }

        /**
         * Handles the result of permission request
         * @param requestCode The request code for the permission
         * @param permissions The requested permissions
         * @param grantResults The grant results for the corresponding permissions
         * @return Boolean indicating if all requested permissions were granted
         */
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ): Boolean {
            return requestCode == WIFI_PERMISSION_REQUEST_CODE &&
                    grantResults.isNotEmpty() &&
                    grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        }
    }
}