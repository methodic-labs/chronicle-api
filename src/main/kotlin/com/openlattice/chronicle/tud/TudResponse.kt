package com.openlattice.chronicle.tud

import java.time.OffsetDateTime
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 */

data class TudResponse(
        @JsonProperty("questionCode") val questionCode: Set<String>,
        @JsonProperty("response") val response: Set<Any>,
        @JsonProperty("startDateTime") val startDateTime: Set<OffsetDateTime>?,
        @JsonProperty("endDateTime") val endDateTime: Set<OffsetDateTime>?
)
