package com.openlattice.chronicle.survey

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class AppUsage(
    val appPackageName: String,
    var appLabel: String?,
    val timestamp: OffsetDateTime,
    val users: List<String> = listOf(),
    val timezone: String,
) {
    init {
        if (appLabel?.isBlank() == true) {
            appLabel = appPackageName
        }
    }
}
