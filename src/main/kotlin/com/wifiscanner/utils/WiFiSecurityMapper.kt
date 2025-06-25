package com.wifiscanner.utils

import com.wifiscanner.models.WiFiSecurityType

/**
 * Utility class for mapping WiFi network capabilities to security types.
 */
object WiFiSecurityMapper {
    /**
     * Maps raw network capabilities to a WiFiSecurityType.
     *
     * @param capabilities Raw network capabilities string
     * @return WiFiSecurityType representing the network's security
     */
    fun mapSecurityType(capabilities: String?): WiFiSecurityType {
        return when {
            capabilities.isNullOrBlank() -> WiFiSecurityType(
                "Unknown",
                "Unable to determine network security",
                0
            )
            capabilities.contains("WPA3", ignoreCase = true) -> WiFiSecurityType(
                "WPA3",
                "Most advanced WiFi security protocol",
                5
            )
            capabilities.contains("WPA2", ignoreCase = true) -> WiFiSecurityType(
                "WPA2",
                "Strong encryption with improved security over WPA",
                4
            )
            capabilities.contains("WPA", ignoreCase = true) -> WiFiSecurityType(
                "WPA",
                "Older security protocol with moderate protection",
                3
            )
            capabilities.contains("WEP", ignoreCase = true) -> WiFiSecurityType(
                "WEP",
                "Weak and outdated security protocol",
                1
            )
            capabilities.contains("Open", ignoreCase = true) -> WiFiSecurityType(
                "Open",
                "No encryption, completely unsecured network",
                0
            )
            else -> WiFiSecurityType(
                "Other",
                "Unrecognized security type",
                2
            )
        }
    }

    /**
     * Generates a color based on the security level.
     *
     * @param securityType WiFiSecurityType to determine color for
     * @return Color resource ID representing security level
     */
    fun getSecurityColor(securityType: WiFiSecurityType): Int {
        return when (securityType.level) {
            0 -> android.R.color.holo_red_dark    // Open/No Security
            1 -> android.R.color.holo_red_light   // Very Weak
            2 -> android.R.color.holo_orange_dark // Weak
            3 -> android.R.color.holo_orange_light // Moderate
            4 -> android.R.color.holo_green_light  // Strong
            5 -> android.R.color.holo_green_dark   // Most Secure
            else -> android.R.color.darker_gray   // Unknown
        }
    }
}