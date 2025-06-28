package com.wifiscanner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wifiscanner.R
import com.wifiscanner.model.WifiNetwork

/**
 * RecyclerView adapter for displaying WiFi networks
 * @property networks List of WiFi networks to display
 */
class WifiNetworkAdapter(private val networks: List<WifiNetwork>) : 
    RecyclerView.Adapter<WifiNetworkAdapter.WifiNetworkViewHolder>() {

    /**
     * ViewHolder for WiFi network items in the RecyclerView
     * @param view The view for a single network item
     */
    class WifiNetworkViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ssidTextView: TextView = view.findViewById(R.id.network_ssid)
        private val strengthTextView: TextView = view.findViewById(R.id.network_strength)
        private val securityTextView: TextView = view.findViewById(R.id.network_security)

        /**
         * Bind WiFi network data to the view
         * @param network The WiFi network to display
         */
        fun bind(network: WifiNetwork) {
            ssidTextView.text = network.ssid
            strengthTextView.apply {
                text = "${network.signalStrength} dBm (${network.getSignalQuality()})"
                setTextColor(ContextCompat.getColor(context, network.getSignalColor()))
            }
            securityTextView.text = network.securityType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiNetworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wifi_network_item, parent, false)
        return WifiNetworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WifiNetworkViewHolder, position: Int) {
        holder.bind(networks[position])
    }

    override fun getItemCount() = networks.size
}