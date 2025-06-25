package com.example.wifiscanner

import android.content.Context
import android.net.wifi.WifiManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.verify
import org.mockito.Mockito.mock

@RunWith(MockitoJUnitRunner::class)
class WifiScannerActivityTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var mockWifiManager: WifiManager

    private lateinit var wifiScannerActivity: WifiScannerActivity

    @Before
    fun setup() {
        // Setup mocks and initialize activity
        wifiScannerActivity = WifiScannerActivity()
    }

    @Test
    fun testScanButtonExists() {
        // Verify that the scan button is present in the layout
        val scanButton = wifiScannerActivity.findViewById<MaterialButton>(R.id.btn_scan_wifi)
        assert(scanButton != null) { "Scan button should be present in the layout" }
    }

    @Test
    fun testRecyclerViewExists() {
        // Verify that the RecyclerView is present in the layout
        val recyclerView = wifiScannerActivity.findViewById<RecyclerView>(R.id.rv_wifi_networks)
        assert(recyclerView != null) { "RecyclerView should be present in the layout" }
    }

    @Test
    fun testWifiNetworkAdapterInitialization() {
        // Verify that the WiFi network adapter is initialized with an empty list
        val recyclerView = wifiScannerActivity.findViewById<RecyclerView>(R.id.rv_wifi_networks)
        assert(recyclerView.adapter is WifiNetworkAdapter) { "Adapter should be an instance of WifiNetworkAdapter" }
    }
}