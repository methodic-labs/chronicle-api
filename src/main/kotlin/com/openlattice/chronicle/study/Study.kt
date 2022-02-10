package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.authorization.AbstractSecurableObject
import com.openlattice.chronicle.authorization.SecurableObjectType
import com.openlattice.chronicle.ids.IdConstants
import com.openlattice.chronicle.sensorkit.SensorType
import com.openlattice.chronicle.storage.ChronicleStorage
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime
import java.time.OffsetDateTime
import java.util.*

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
class Study @JsonCreator constructor(studyId: UUID = IdConstants.UNINITIALIZED.id,
    title: String,
    description: String = "",
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    val updatedAt: OffsetDateTime = OffsetDateTime.now(),
    val startedAt: OffsetDateTime = OffsetDateTime.now(),
    val endedAt: OffsetDateTime = OffsetDateTime.MAX,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val group: String = "",
    val version: String = "",
    val contact: String,
    val organizationIds: Set<UUID> = setOf(),
    val notificationsEnabled: Boolean = false,
    var storage: String = ChronicleStorage.CHRONICLE.id,
    val settings: Map<String, Any> = mapOf(),
) : AbstractSecurableObject(studyId, title, description) {
    init {
        check( storage.length <= 36 && StringUtils.isAlpha(storage)) {
            "Storage name cannot be more 36 characters and must also be alphabetic characters only"
        }
    }
    companion object {
        const val SENSORS = "sensors"
    }
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
//                   contact :String,
//                   organizationIds: Set<UUID>,
//                   settings: Map<String, Any>
//    ) : this( studyId, title, description, createdAt, updatedAt, startedAt, endedAt, lat, lon, group, version, organizationIds, settings)

    override val category: SecurableObjectType = SecurableObjectType.Study
    override fun equals(other: Any?): Boolean {
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
        if (contact != other.contact) return false
        if (organizationIds != other.organizationIds) return false
        if (notificationsEnabled != other.notificationsEnabled) return false
        if (storage != other.storage) return false
        if (settings != other.settings) return false
        if (category != other.category) return false

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
        result = 31 * result + contact.hashCode()
        result = 31 * result + organizationIds.hashCode()
        result = 31 * result + notificationsEnabled.hashCode()
        result = 31 * result + storage.hashCode()
        result = 31 * result + settings.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String {
        return "Study(createdAt=$createdAt, updatedAt=$updatedAt, startedAt=$startedAt, endedAt=$endedAt, lat=$lat, lon=$lon, group='$group', version='$version', contact='$contact', organizationIds=$organizationIds, notificationsEnabled=$notificationsEnabled, storage='$storage', settings=$settings, category=$category)"
    }

    @Suppress("UNCHECKED_CAST")
    fun retrieveConfiguredSensors(): Set<SensorType> {
        if (!settings.containsKey(SENSORS)) return setOf()

        return (settings.getValue(SENSORS) as List<String>).map { SensorType.valueOf(it) }.toSet()
    }

}
