package com.wifiscanner.models

/**
 * Represents the security type of a WiFi network.
 *
 * @property type The human-readable security type
 * @property description A detailed description of the security type
 * @property level A numeric representation of security strength (higher is more secure)
 */
data class WiFiSecurityType(
    val type: String,
    val description: String,
    val level: Int
)