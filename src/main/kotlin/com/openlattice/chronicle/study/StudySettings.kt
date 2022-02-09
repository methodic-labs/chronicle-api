package com.openlattice.chronicle.study

import com.openlattice.chronicle.sensorkit.SensorType

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */

class StudySettings(val settings: Map<String, Any>) {

    companion object {
        const val SENSORS = "sensors"
    }

    @Suppress("UNCHECKED_CAST")
    fun getConfiguredSensors(): Set<SensorType> {
        if (!settings.containsKey(SENSORS)) return setOf()

        return (settings.getValue(SENSORS) as List<String>).map { SensorType.valueOf(it) }.toSet()
    }
}
