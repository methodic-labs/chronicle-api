package com.openlattice.chronicle.survey

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class AppUsage(
        var id: UUID,
        var appPackageName: String,
        var appLabel: String,
        var timeStamp: OffsetDateTime,
)
