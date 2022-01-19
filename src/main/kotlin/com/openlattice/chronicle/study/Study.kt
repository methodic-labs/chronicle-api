package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.authorization.AbstractSecurableObject
import com.openlattice.chronicle.authorization.SecurableObjectType
import com.openlattice.chronicle.ids.IdConstants
import org.joda.time.DateTime
import java.time.OffsetDateTime
import java.util.*

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
class Study @JsonCreator constructor(
    @JsonProperty("studyId") studyId: UUID = IdConstants.UNINITIALIZED.id,
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

    override fun equals(other: Any?): Boolean {1
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as Study

        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (startedAt != other.startedAt) return false
        if (endedAt != other.endedAt) return false
        if (lat != other.lat) return false
        if (lon != other.lon) return false
        if (group != other.group) return false
        if (version != other.version) return false
        if (organizationIds != other.organizationIds) return false
        if (settings != other.settings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + startedAt.hashCode()
        result = 31 * result + endedAt.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + lon.hashCode()
        result = 31 * result + group.hashCode()
        result = 31 * result + version.hashCode()
        result = 31 * result + organizationIds.hashCode()
        result = 31 * result + settings.hashCode()
        return result
    }

}
