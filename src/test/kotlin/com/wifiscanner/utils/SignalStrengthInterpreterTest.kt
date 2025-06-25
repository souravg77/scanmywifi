package com.wifiscanner.utils

import org.junit.Test
import org.junit.Assert.*

class SignalStrengthInterpreterTest {

    @Test
    fun `test signal strength interpretation`() {
        // Excellent range
        assertEquals("Excellent", SignalStrengthInterpreter.interpretSignalStrength(-40))
        
        // Very Good range
        assertEquals("Very Good", SignalStrengthInterpreter.interpretSignalStrength(-55))
        
        // Good range
        assertEquals("Good", SignalStrengthInterpreter.interpretSignalStrength(-65))
        
        // Fair range
        assertEquals("Fair", SignalStrengthInterpreter.interpretSignalStrength(-75))
        
        // Poor range
        assertEquals("Poor", SignalStrengthInterpreter.interpretSignalStrength(-85))
        
        // Very Poor range
        assertEquals("Very Poor", SignalStrengthInterpreter.interpretSignalStrength(-95))
    }

    @Test
    fun `test signal strength color resources`() {
        // Excellent range color
        assertEquals(android.R.color.holo_green_light, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-40))
        
        // Very Good range color
        assertEquals(android.R.color.holo_green_dark, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-55))
        
        // Good range color
        assertEquals(android.R.color.holo_orange_light, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-65))
        
        // Fair range color
        assertEquals(android.R.color.holo_orange_dark, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-75))
        
        // Poor range color
        assertEquals(android.R.color.holo_red_light, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-85))
        
        // Very Poor range color
        assertEquals(android.R.color.holo_red_dark, 
            SignalStrengthInterpreter.getSignalStrengthColorResource(-95))
    }
}