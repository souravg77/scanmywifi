package com.example.wifiscanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WifiNetworkAdapter(private val networks: List<WifiNetwork>) : 
    RecyclerView.Adapter<WifiNetworkAdapter.WifiNetworkViewHolder>() {

    class WifiNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WifiNetworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wifi_network, parent, false)
        return WifiNetworkViewHolder(view)
    }

    override fun onBindViewHolder(holder: WifiNetworkViewHolder, position: Int) {
        // Placeholder for future implementation
    }

    override fun getItemCount(): Int = networks.size
}