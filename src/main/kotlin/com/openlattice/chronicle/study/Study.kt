package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonProperty
import org.joda.time.DateTime
import java.util.*

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
data class Study(
    @JsonProperty("organizationId") val organizationId: UUID,
    @JsonProperty("studyId") val studyId: UUID?,
    @JsonProperty("title") val title :String,
    @JsonProperty("description") val description :String,
    @JsonProperty("createdAt") val createdAt: DateTime?,
    @JsonProperty("updatedAt") val updatedAt: DateTime?,
    @JsonProperty("startedAt") val startedAt: DateTime?,
    @JsonProperty("endedAt") val endedAt: DateTime?,
//    @JsonProperty("location") val location:
    @JsonProperty("group") val group: String?,
    @JsonProperty("version") val version: String?,
//    @JsonProperty("settings") val settings :Map,
)
