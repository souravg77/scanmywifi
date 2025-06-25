package com.wifiscanner.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [29]) // Target Android 10
class WifiPermissionManagerTest {
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun `hasWifiPermissions returns false when permissions not granted`() {
        // Mock context to simulate no permissions
        val mockContext = mock(Context::class.java)
        `when`(mockContext.checkPermission(
            eq(Manifest.permission.ACCESS_FINE_LOCATION),
            anyInt(),
            anyInt()
        )).thenReturn(PackageManager.PERMISSION_DENIED)

        assertFalse(WifiPermissionManager.hasWifiPermissions(mockContext))
    }

    @Test
    fun `onRequestPermissionsResult returns true when all permissions granted`() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        )
        val grantResults = intArrayOf(
            PackageManager.PERMISSION_GRANTED,
            PackageManager.PERMISSION_GRANTED
        )

        assertTrue(
            WifiPermissionManager.onRequestPermissionsResult(
                WifiPermissionManager.WIFI_PERMISSION_REQUEST_CODE,
                permissions,
                grantResults
            )
        )
    }

    @Test
    fun `onRequestPermissionsResult returns false when any permission denied`() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE
        )
        val grantResults = intArrayOf(
            PackageManager.PERMISSION_GRANTED,
            PackageManager.PERMISSION_DENIED
        )

        assertFalse(
            WifiPermissionManager.onRequestPermissionsResult(
                WifiPermissionManager.WIFI_PERMISSION_REQUEST_CODE,
                permissions,
                grantResults
            )
        )
    }
}