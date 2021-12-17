package com.openlattice.chronicle.tud

import java.time.OffsetDateTime

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 */

data class TudResponse(
        val questionCode: String,
        val response: Set<Any>,
        val startDateTime: OffsetDateTime?,
        val endDateTime: OffsetDateTime?
)
