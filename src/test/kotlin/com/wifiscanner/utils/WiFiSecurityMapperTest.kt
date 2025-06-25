package com.wifiscanner.utils

import com.wifiscanner.models.WiFiSecurityType
import org.junit.Assert.*
import org.junit.Test

class WiFiSecurityMapperTest {

    @Test
    fun `mapSecurityType should return WPA3 for WPA3 capabilities`() {
        val capabilities = "[WPA3-PSK-CCMP]"
        val result = WiFiSecurityMapper.mapSecurityType(capabilities)
        
        assertEquals("WPA3", result.type)
        assertEquals("Most advanced WiFi security protocol", result.description)
        assertEquals(5, result.level)
    }

    @Test
    fun `mapSecurityType should return WPA2 for WPA2 capabilities`() {
        val capabilities = "[WPA2-PSK-CCMP]"
        val result = WiFiSecurityMapper.mapSecurityType(capabilities)
        
        assertEquals("WPA2", result.type)
        assertEquals("Strong encryption with improved security over WPA", result.description)
        assertEquals(4, result.level)
    }

    @Test
    fun `mapSecurityType should return Open for open network`() {
        val capabilities = ""
        val result = WiFiSecurityMapper.mapSecurityType(capabilities)
        
        assertEquals("Open", result.type)
        assertEquals("No encryption, completely unsecured network", result.description)
        assertEquals(0, result.level)
    }

    @Test
    fun `mapSecurityType should return Unknown for null capabilities`() {
        val capabilities = null
        val result = WiFiSecurityMapper.mapSecurityType(capabilities)
        
        assertEquals("Unknown", result.type)
        assertEquals("Unable to determine network security", result.description)
        assertEquals(0, result.level)
    }

    @Test
    fun `getSecurityColor returns correct color for different security levels`() {
        val openSecurity = WiFiSecurityType("Open", "Unsecured", 0)
        val wpa3Security = WiFiSecurityType("WPA3", "Secure", 5)
        
        assertEquals(android.R.color.holo_red_dark, WiFiSecurityMapper.getSecurityColor(openSecurity))
        assertEquals(android.R.color.holo_green_dark, WiFiSecurityMapper.getSecurityColor(wpa3Security))
    }
}