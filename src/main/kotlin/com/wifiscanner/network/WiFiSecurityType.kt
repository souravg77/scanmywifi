package com.wifiscanner.network

/**
 * Represents different WiFi security types
 */
enum class WiFiSecurityType {
    OPEN,       // No security
    WEP,        // Wired Equivalent Privacy (deprecated)
    WPA,        // Wi-Fi Protected Access
    WPA2,       // Wi-Fi Protected Access 2
    WPA3,       // Wi-Fi Protected Access 3
    UNKNOWN     // Unable to determine security type
}

/**
 * Utility class for detecting WiFi network security types
 */
object WiFiSecurityDetector {
    /**
     * Detects the security type of a WiFi network based on its capabilities
     * @param capabilities The capabilities string from WiFi scan results
     * @return Detected WiFiSecurityType
     */
    fun detectSecurityType(capabilities: String?): WiFiSecurityType {
        if (capabilities.isNullOrBlank()) {
            return WiFiSecurityType.UNKNOWN
        }

        val uppercaseCapabilities = capabilities.uppercase()

        return when {
            uppercaseCapabilities.contains("[WPA3]") -> WiFiSecurityType.WPA3
            uppercaseCapabilities.contains("[WPA2]") -> WiFiSecurityType.WPA2
            uppercaseCapabilities.contains("[WPA]") -> WiFiSecurityType.WPA
            uppercaseCapabilities.contains("[WEP]") -> WiFiSecurityType.WEP
            uppercaseCapabilities.contains("[ESS]") -> WiFiSecurityType.OPEN
            else -> WiFiSecurityType.UNKNOWN
        }
    }
}