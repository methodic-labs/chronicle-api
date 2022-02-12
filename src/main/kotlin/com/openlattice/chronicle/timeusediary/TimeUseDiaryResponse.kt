package com.openlattice.chronicle.timeusediary

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 */

data class TimeUseDiaryResponse(

        @JsonProperty("ol.code")
        @JsonAlias("code")
        val code: String,

        @JsonProperty("ol.title")
        @JsonAlias("question")
        val question: String,

        @JsonProperty("ol.values")
        @JsonAlias("response")
        val response: Set<String>,

        @JsonProperty("ol.datetimestart")
        @JsonAlias("startDateTime")
        val startDateTime: OffsetDateTime?,

        @JsonProperty("ol.datetimeend")
        @JsonAlias("endDateTime")
        val endDateTime: OffsetDateTime?
)
