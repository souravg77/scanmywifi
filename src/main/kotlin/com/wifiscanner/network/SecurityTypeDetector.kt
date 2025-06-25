package com.wifiscanner.network

import android.net.wifi.ScanResult
import android.util.Log

/**
 * Enum representing WiFi security types with error handling capabilities
 */
enum class SecurityType {
    OPEN,
    WEP,
    WPA,
    WPA2,
    WPA3,
    UNKNOWN
}

/**
 * Handles detection of WiFi network security types with robust error management
 */
class SecurityTypeDetector {
    companion private const val TAG = "SecurityTypeDetector"

    /**
     * Detect security type of a WiFi network with comprehensive error handling
     * @param scanResult The WiFi scan result to analyze
     * @return Detected security type, defaults to UNKNOWN if detection fails
     */
    fun detectSecurityType(scanResult: ScanResult?): SecurityType {
        // Handle null input gracefully
        if (scanResult == null) {
            Log.w(TAG, "Null ScanResult provided. Returning UNKNOWN security type.")
            return SecurityType.UNKNOWN
        }

        return try {
            when {
                // Check for specific security capabilities
                scanResult.capabilities.contains("WPA3", ignoreCase = true) -> SecurityType.WPA3
                scanResult.capabilities.contains("WPA2", ignoreCase = true) -> SecurityType.WPA2
                scanResult.capabilities.contains("WPA", ignoreCase = true) -> SecurityType.WPA
                scanResult.capabilities.contains("WEP", ignoreCase = true) -> SecurityType.WEP
                scanResult.capabilities.isEmpty() -> SecurityType.OPEN
                else -> {
                    // Log non-standard or unrecognized security type
                    Log.i(TAG, "Unrecognized security type: ${scanResult.capabilities}")
                    SecurityType.UNKNOWN
                }
            }
        } catch (e: Exception) {
            // Catch and log any unexpected errors during security type detection
            Log.e(TAG, "Error detecting security type: ${e.message}", e)
            SecurityType.UNKNOWN
        }
    }

    /**
     * Provides a human-readable description of the security type
     * @param securityType The security type to describe
     * @return A descriptive string of the security type
     */
    fun getSecurityDescription(securityType: SecurityType): String {
        return when (securityType) {
            SecurityType.OPEN -> "Open Network (No Security)"
            SecurityType.WEP -> "WEP Protected Network"
            SecurityType.WPA -> "WPA Protected Network"
            SecurityType.WPA2 -> "WPA2 Protected Network"
            SecurityType.WPA3 -> "WPA3 Protected Network"
            SecurityType.UNKNOWN -> "Unknown Security Type"
        }
    }
}