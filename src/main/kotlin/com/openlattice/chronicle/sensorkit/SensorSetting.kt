package com.openlattice.chronicle.sensorkit

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.study.StudySetting

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 *
 * This class has to manually delegate equals and hashCode since they are part of Object and not Set.
 *
 * Creating a marker interface doesn't work in this case since you can't easily construct things that impl that
 * interface for delegation, so this ends up being cleaner.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
class SensorSetting(private val sensors: Set<SensorType>) : StudySetting, Set<SensorType> by sensors {
    companion object {
        val NO_SENSORS = SensorSetting(emptySet())
    }

    override fun equals(other: Any?): Boolean {
        return sensors == other
    }

    override fun hashCode(): Int {
        return sensors.hashCode()
    }


}