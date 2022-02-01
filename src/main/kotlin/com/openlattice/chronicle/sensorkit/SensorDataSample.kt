package com.openlattice.chronicle.sensorkit

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class SensorDataSample (
        val id: UUID,
        val dateRecorded: OffsetDateTime,
        val duration: Double,
        val data: String,
        val device: String,
        val timezone: String,
        val sensor: String
)