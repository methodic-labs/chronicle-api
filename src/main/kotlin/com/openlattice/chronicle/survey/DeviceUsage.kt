package com.openlattice.chronicle.survey

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class DeviceUsage(
    val startTime: OffsetDateTime,
    val endTime: OffsetDateTime,
    val timezone: String,
    val users: List<String> = listOf(),
)
