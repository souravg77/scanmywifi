package com.wifiscanner.network

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WiFiSecurityDetectorTest {

    @Test
    fun `detect WPA3 security type`() {
        val capabilities = "[WPA3-PSK-CCMP]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.WPA3, securityType)
    }

    @Test
    fun `detect WPA2 security type`() {
        val capabilities = "[WPA2-PSK-CCMP]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.WPA2, securityType)
    }

    @Test
    fun `detect WPA security type`() {
        val capabilities = "[WPA-PSK-TKIP]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.WPA, securityType)
    }

    @Test
    fun `detect WEP security type`() {
        val capabilities = "[WEP]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.WEP, securityType)
    }

    @Test
    fun `detect open network security type`() {
        val capabilities = "[ESS]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.OPEN, securityType)
    }

    @Test
    fun `detect unknown security type`() {
        val capabilities = "[CUSTOM]"
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.UNKNOWN, securityType)
    }

    @Test
    fun `handle null capabilities`() {
        val capabilities = null
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.UNKNOWN, securityType)
    }

    @Test
    fun `handle empty capabilities`() {
        val capabilities = ""
        val securityType = WiFiSecurityDetector.detectSecurityType(capabilities)
        assertEquals(WiFiSecurityType.UNKNOWN, securityType)
    }
}