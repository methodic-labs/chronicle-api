package com.openlattice.chronicle.timeusediary

import java.time.OffsetDateTime

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 */

data class TimeUseDiaryResponse(
        val questionCode: Set<String>,
        val response: Set<String>,
        val startDateTime: Set<OffsetDateTime> = setOf(),
        val endDateTime: Set<OffsetDateTime> = setOf()
)
