package com.openlattice.chronicle.timeusediary

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 */

data class TimeUseDiaryResponse(
        @JsonProperty("questionCode") val questionCode: Set<String>,
        @JsonProperty("response") val response: Set<String>,
        @JsonProperty("startDateTime") val startDateTime: Set<OffsetDateTime> = setOf(),
        @JsonProperty("endDateTime") val endDateTime: Set<OffsetDateTime> = setOf()
)
