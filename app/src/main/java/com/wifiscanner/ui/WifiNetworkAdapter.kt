package com.wifiscanner.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wifiscanner.R
import com.wifiscanner.model.WifiNetwork

/**
 * RecyclerView adapter for displaying WiFi networks
 *
 * @property networks List of WiFi networks to display
 */
class WifiNetworkAdapter(private val networks: List<WifiNetwork>) : 
    RecyclerView.Adapter<WifiNetworkAdapter.WifiNetworkViewHolder>() {

    /**
     * ViewHolder for WiFi network items
     *
     * @param itemView The view for a single WiFi network item
     */
    class WifiNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ssidTextView: TextView = itemView.findViewById(R.id.wifi_ssid)
        private val signalStrengthTextView: TextView = itemView.findViewById(R.id.wifi_signal_strength)
        private val securityTypeTextView: TextView = itemView.findViewById(R.id.wifi_security_type)

        /**
         * Bind WiFi network data to the view
         *
         * @param network The WiFi network to display
         */
        fun bind(network: WifiNetwork) {
            ssidTextView.text = network.ssid
            signalStrengthTextView.text = network.getSignalStrengthDescription()
            securityTypeTextView.text = network.securityType.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiNetworkViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.wifi_network_item, parent, false)
        return WifiNetworkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WifiNetworkViewHolder, position: Int) {
        holder.bind(networks[position])
    }

    override fun getItemCount() = networks.size
}