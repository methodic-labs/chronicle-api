package com.openlattice.chronicle.survey

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class DeviceUsage(
    val totalTime: Double,
    val usageByPackage: Map<String, Double>,
    val categoryByPackage: Map<String, String>,
    val users: List<String> = listOf()
)
