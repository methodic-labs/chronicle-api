package com.openlattice.chronicle.sensorkit

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 * An instance of this class encapsulates a sample recorded by SensorKit framework
 */

data class SensorDataSample (
        val id: UUID,
        val dateRecorded: OffsetDateTime,
        val duration: Double,
        val data: String,
        val timezone: String,
        val sensor: String,
        val startDate: OffsetDateTime,
        val endDate: OffsetDateTime
)
