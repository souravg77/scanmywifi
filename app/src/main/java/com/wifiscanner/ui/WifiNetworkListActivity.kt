package com.wifiscanner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wifiscanner.R
import com.wifiscanner.model.WifiNetwork

/**
 * Activity to display the list of WiFi networks
 */
class WifiNetworkListActivity : AppCompatActivity() {

    private lateinit var wifiNetworkRecyclerView: RecyclerView
    private lateinit var wifiNetworkAdapter: WifiNetworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_network_list)

        // Initialize RecyclerView
        wifiNetworkRecyclerView = findViewById(R.id.wifi_network_recycler_view)
        wifiNetworkRecyclerView.layoutManager = LinearLayoutManager(this)

        // Sample data for initial setup (replace with actual WiFi scanning logic)
        val sampleNetworks = listOf(
            WifiNetwork("Home WiFi", "00:11:22:33:44:55", -55, WifiNetwork.SecurityType.WPA2, 2400),
            WifiNetwork("Office Network", "AA:BB:CC:DD:EE:FF", -65, WifiNetwork.SecurityType.WPA3, 5000),
            WifiNetwork("Public WiFi", "11:22:33:44:55:66", -75, WifiNetwork.SecurityType.OPEN, 2400)
        )

        // Set up adapter
        wifiNetworkAdapter = WifiNetworkAdapter(sampleNetworks)
        wifiNetworkRecyclerView.adapter = wifiNetworkAdapter
    }
}