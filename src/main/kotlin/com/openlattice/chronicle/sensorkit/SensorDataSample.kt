package com.openlattice.chronicle.sensorkit

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class SensorDataSample (
        val dateRecorded: OffsetDateTime,
        val startDate: OffsetDateTime,
        val endDate: OffsetDateTime,
        val data: Map<String, Any>,
        val timezone: String,
        val id: UUID,
        val sensorName: String
)