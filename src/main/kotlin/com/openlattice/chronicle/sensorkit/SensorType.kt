package com.openlattice.chronicle.sensorkit

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 * Describes supported sensor types
 */


enum class SensorType(sensorType: String){
    DEVICE_USAGE("deviceUsage"),
    KEYBOARD_METRICS("keyboardMetrics"),
    MESSAGES_USAGE("messagesUpdate"),
    PHONE_USAGE("phoneUsage")
}