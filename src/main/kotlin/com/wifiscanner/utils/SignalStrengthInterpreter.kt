package com.wifiscanner.utils

/**
 * Utility class for interpreting WiFi signal strength levels
 * Provides human-readable descriptions of signal strength
 */
object SignalStrengthInterpreter {
    /**
     * Interprets signal strength level and returns a descriptive text
     * @param signalLevel Signal strength level (typically in dBm)
     * @return A descriptive text representing the signal strength
     */
    fun interpretSignalStrength(signalLevel: Int): String {
        return when {
            signalLevel >= -50 -> "Excellent"
            signalLevel >= -60 -> "Very Good"
            signalLevel >= -70 -> "Good"
            signalLevel >= -80 -> "Fair"
            signalLevel >= -90 -> "Poor"
            else -> "Very Poor"
        }
    }

    /**
     * Provides a color representation for the signal strength
     * @param signalLevel Signal strength level (typically in dBm)
     * @return Color resource representing signal strength
     */
    fun getSignalStrengthColorResource(signalLevel: Int): Int {
        return when {
            signalLevel >= -50 -> android.R.color.holo_green_light
            signalLevel >= -60 -> android.R.color.holo_green_dark
            signalLevel >= -70 -> android.R.color.holo_orange_light
            signalLevel >= -80 -> android.R.color.holo_orange_dark
            signalLevel >= -90 -> android.R.color.holo_red_light
            else -> android.R.color.holo_red_dark
        }
    }
}