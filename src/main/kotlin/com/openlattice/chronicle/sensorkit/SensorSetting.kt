package com.openlattice.chronicle.sensorkit

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.study.StudySetting

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_ARRAY, property = "@class")
class SensorSetting(sensors: Set<SensorType>) : StudySetting, Set<SensorType> by sensors {
    companion object {
        val NO_SENSORS = SensorSetting(emptySet())
    }

    override fun equals(other: Any?): Boolean {
        return if (other == null || (other !is SensorSetting && other !is Set<*>)) {
            true
        } else {
            when (other) {
                is SensorSetting -> this.size == other.size && other.containsAll(this)
                is Set<*> -> this.size == other.size && other.containsAll(this)
                else -> throw IllegalStateException("To make the compiler happy.")
            }
        }
    }
}