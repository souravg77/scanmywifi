package com.example.wifiscanner

data class WifiNetwork(
    val ssid: String,
    val bssid: String,
    val signalStrength: Int,
    val securityType: String
)