package com.wifiscanner.model

/**
 * Data class representing a WiFi network with its details
 *
 * @property ssid The name of the WiFi network
 * @property bssid The MAC address of the WiFi access point
 * @property signalStrength The signal strength of the WiFi network in dBm
 * @property securityType The security type of the WiFi network
 * @property frequency The frequency of the WiFi network in MHz
 */
data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: SecurityType,
    val frequency: Int
) {
    /**
     * Enum representing WiFi network security types
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
     * Calculate a human-readable signal strength description
     *
     * @return A string describing the signal strength
     */
    fun getSignalStrengthDescription(): String {
        return when {
            signalStrength >= -50 -> "Excellent"
            signalStrength >= -60 -> "Good"
            signalStrength >= -70 -> "Fair"
            else -> "Weak"
        }
    }
}