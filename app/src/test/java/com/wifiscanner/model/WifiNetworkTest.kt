package com.wifiscanner.model

import org.junit.Assert.*
import org.junit.Test

class WifiNetworkTest {

    @Test
    fun `test wifi network signal strength description`() {
        val excellentSignal = WifiNetwork(
            "Test Network", 
            "00:11:22:33:44:55", 
            -45, 
            WifiNetwork.SecurityType.WPA2, 
            2400
        )
        assertEquals("Excellent", excellentSignal.getSignalStrengthDescription())

        val goodSignal = WifiNetwork(
            "Test Network", 
            "00:11:22:33:44:55", 
            -55, 
            WifiNetwork.SecurityType.WPA2, 
            2400
        )
        assertEquals("Good", goodSignal.getSignalStrengthDescription())

        val fairSignal = WifiNetwork(
            "Test Network", 
            "00:11:22:33:44:55", 
            -65, 
            WifiNetwork.SecurityType.WPA2, 
            2400
        )
        assertEquals("Fair", fairSignal.getSignalStrengthDescription())

        val weakSignal = WifiNetwork(
            "Test Network", 
            "00:11:22:33:44:55", 
            -80, 
            WifiNetwork.SecurityType.WPA2, 
            2400
        )
        assertEquals("Weak", weakSignal.getSignalStrengthDescription())
    }

    @Test
    fun `test wifi network data class properties`() {
        val network = WifiNetwork(
            "Home WiFi", 
            "00:11:22:33:44:55", 
            -55, 
            WifiNetwork.SecurityType.WPA2, 
            2400
        )

        assertEquals("Home WiFi", network.ssid)
        assertEquals("00:11:22:33:44:55", network.bssid)
        assertEquals(-55, network.signalStrength)
        assertEquals(WifiNetwork.SecurityType.WPA2, network.securityType)
        assertEquals(2400, network.frequency)
    }
}