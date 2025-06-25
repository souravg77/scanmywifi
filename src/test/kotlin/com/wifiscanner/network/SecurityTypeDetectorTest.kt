package com.wifiscanner.network

import android.net.wifi.ScanResult
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SecurityTypeDetectorTest {

    private val detector = SecurityTypeDetector()

    @Test
    fun `test null scan result returns UNKNOWN`() {
        val result = detector.detectSecurityType(null)
        assertEquals(SecurityType.UNKNOWN, result)
    }

    @Test
    fun `test open network detection`() {
        val mockScanResult = Mockito.mock(ScanResult::class.java)
        Mockito.`when`(mockScanResult.capabilities).thenReturn("")
        
        val result = detector.detectSecurityType(mockScanResult)
        assertEquals(SecurityType.OPEN, result)
    }

    @Test
    fun `test WPA3 network detection`() {
        val mockScanResult = Mockito.mock(ScanResult::class.java)
        Mockito.`when`(mockScanResult.capabilities).thenReturn("[WPA3-PSK-CCMP]")
        
        val result = detector.detectSecurityType(mockScanResult)
        assertEquals(SecurityType.WPA3, result)
    }

    @Test
    fun `test WPA2 network detection`() {
        val mockScanResult = Mockito.mock(ScanResult::class.java)
        Mockito.`when`(mockScanResult.capabilities).thenReturn("[WPA2-PSK-CCMP]")
        
        val result = detector.detectSecurityType(mockScanResult)
        assertEquals(SecurityType.WPA2, result)
    }

    @Test
    fun `test WPA network detection`() {
        val mockScanResult = Mockito.mock(ScanResult::class.java)
        Mockito.`when`(mockScanResult.capabilities).thenReturn("[WPA-PSK-CCMP]")
        
        val result = detector.detectSecurityType(mockScanResult)
        assertEquals(SecurityType.WPA, result)
    }

    @Test
    fun `test WEP network detection`() {
        val mockScanResult = Mockito.mock(ScanResult::class.java)
        Mockito.`when`(mockScanResult.capabilities).thenReturn("[WEP]")
        
        val result = detector.detectSecurityType(mockScanResult)
        assertEquals(SecurityType.WEP, result)
    }

    @Test
    fun `test security description mapping`() {
        assertEquals("Open Network (No Security)", detector.getSecurityDescription(SecurityType.OPEN))
        assertEquals("WEP Protected Network", detector.getSecurityDescription(SecurityType.WEP))
        assertEquals("WPA Protected Network", detector.getSecurityDescription(SecurityType.WPA))
        assertEquals("WPA2 Protected Network", detector.getSecurityDescription(SecurityType.WPA2))
        assertEquals("WPA3 Protected Network", detector.getSecurityDescription(SecurityType.WPA3))
        assertEquals("Unknown Security Type", detector.getSecurityDescription(SecurityType.UNKNOWN))
    }
}