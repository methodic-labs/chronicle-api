package com.openlattice.chronicle.tud

import java.time.OffsetDateTime
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
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
