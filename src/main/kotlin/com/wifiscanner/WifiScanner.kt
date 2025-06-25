package com.wifiscanner

import android.content.Context
import android.net.wifi.WifiManager
import android.net.wifi.ScanResult
import android.Manifest
import androidx.annotation.RequiresPermission

/**
 * WifiScanner provides functionality to scan and retrieve available WiFi networks.
 * 
 * @property context Android application context
 */
class WifiScanner(private val context: Context) {

    private val wifiManager: WifiManager by lazy {
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    /**
     * Scans for available WiFi networks.
     * 
     * @return List of WiFi networks found during the scan
     * @throws SecurityException if location permissions are not granted
     */
    @RequiresPermission(
        anyOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ]
    )
    fun scanWifiNetworks(): List<WifiNetwork> {
        // Check if WiFi is enabled
        if (!wifiManager.isWifiEnabled) {
            throw IllegalStateException("WiFi is not enabled")
        }

        // Initiate WiFi scan
        val scanSuccess = wifiManager.startScan()
        
        if (!scanSuccess) {
            throw RuntimeException("WiFi scan failed to start")
        }

        // Retrieve scan results
        val scanResults = wifiManager.scanResults ?: emptyList()

        // Map scan results to WifiNetwork objects
        return scanResults.map { result ->
            WifiNetwork(
                ssid = result.SSID ?: "Unknown",
                bssid = result.BSSID ?: "",
                signalStrength = result.level,
                securityType = parseSecurityType(result)
            )
        }
    }

    /**
     * Determines the security type of a WiFi network.
     * 
     * @param scanResult The WiFi scan result
     * @return SecurityType of the network
     */
    private fun parseSecurityType(scanResult: ScanResult): SecurityType {
        return when {
            scanResult.capabilities.contains("WPA3") -> SecurityType.WPA3
            scanResult.capabilities.contains("WPA2") -> SecurityType.WPA2
            scanResult.capabilities.contains("WPA") -> SecurityType.WPA
            scanResult.capabilities.contains("WEP") -> SecurityType.WEP
            else -> SecurityType.OPEN
        }
    }
}

/**
 * Represents a WiFi network with its details.
 */
data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: SecurityType
)

/**
 * Enum representing different WiFi security types.
 */
enum class SecurityType {
    OPEN,   // No security
    WEP,    // Wired Equivalent Privacy
    WPA,    // Wi-Fi Protected Access
    WPA2,   // Wi-Fi Protected Access 2
    WPA3    // Wi-Fi Protected Access 3
}