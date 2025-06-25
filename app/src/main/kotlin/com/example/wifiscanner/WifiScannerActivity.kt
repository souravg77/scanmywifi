package com.example.wifiscanner

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.recyclerview.RecyclerView

class WifiScannerActivity : AppCompatActivity() {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    private lateinit var wifiManager: WifiManager
    private lateinit var scanButton: MaterialButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var wifiNetworkAdapter: WifiNetworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi_scanner)

        // Initialize WiFi Manager
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // Setup RecyclerView
        recyclerView = findViewById(R.id.rv_wifi_networks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        wifiNetworkAdapter = WifiNetworkAdapter(emptyList())
        recyclerView.adapter = wifiNetworkAdapter

        // Setup Scan Button
        scanButton = findViewById(R.id.btn_scan_wifi)
        scanButton.setOnClickListener {
            performWifiScan()
        }
    }

    private fun performWifiScan() {
        // Check and request permissions
        if (checkLocationPermission()) {
            // Actual scanning logic will be implemented in future tasks
            Toast.makeText(this, "Scanning for WiFi networks...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkLocationPermission(): Boolean {
        // Check if location permissions are granted
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request permissions
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performWifiScan()
            } else {
                Toast.makeText(this, "Location permission is required for WiFi scanning", Toast.LENGTH_SHORT).show()
            }
        }
    }
}