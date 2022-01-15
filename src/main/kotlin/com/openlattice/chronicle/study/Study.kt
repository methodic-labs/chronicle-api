package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.authorization.AbstractSecurableObject
import com.openlattice.chronicle.authorization.SecurableObjectType
import org.joda.time.DateTime
import java.time.OffsetDateTime
import java.util.*

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
class Study @JsonCreator constructor(
    @JsonProperty("studyId") studyId: UUID = UUID(0,0),
    @JsonProperty("title") title : String,
    @JsonProperty("description") description :String = "",
    @JsonProperty("createdAt") val createdAt: OffsetDateTime = OffsetDateTime.now(),
    @JsonProperty("updatedAt") val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    @JsonProperty("startedAt") val startedAt: OffsetDateTime = OffsetDateTime.now(),
    @JsonProperty("endedAt") val endedAt: OffsetDateTime = OffsetDateTime.MAX,
    @JsonProperty("lat") val lat: Double = 0.0,
    @JsonProperty("lon") val lon: Double = 0.0,
    @JsonProperty("group") val group: String = "",
    @JsonProperty("version") val version: String = "",
    @JsonProperty("organizationIds") val organizationIds: Set<UUID> = setOf(),
    @JsonProperty("settings") val settings: Map<String, Any> = mapOf(),
) :  AbstractSecurableObject(studyId, title, description) {
//    constructor(   studyId: UUID,
//                   title: String,
//                   description :String,
//                   createdAt: DateTime,
//                   updatedAt: DateTime,
//                   startedAt: DateTime,
//                   endedAt: DateTime?,
//                   lat: Double,
//                   lon: Double,
//                   group: String,
//                   version: String,
//                   organizationIds: Set<UUID>,
//                   settings: Map<String, Any>
//    ) : this( studyId, title, description, createdAt, updatedAt, startedAt, endedAt, lat, lon, group, version, organizationIds, settings)
    override val category: SecurableObjectType = SecurableObjectType.Study
}
