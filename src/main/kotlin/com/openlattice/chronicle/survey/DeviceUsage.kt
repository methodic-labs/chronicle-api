package com.openlattice.chronicle.survey

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class DeviceUsage(
    val totalTime: Long,
    val usageByPackage: Map<String, Long>,
    val categoryByPackage: Map<String, String>,
    val users: List<String> = listOf()
)
