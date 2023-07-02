package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.openlattice.chronicle.authorization.AbstractSecurableObject
import com.openlattice.chronicle.authorization.SecurableObjectType
import com.openlattice.chronicle.ids.IdConstants
import com.openlattice.chronicle.notifications.StudyNotificationSettings
import com.openlattice.chronicle.sensorkit.SensorSetting
import com.openlattice.chronicle.sensorkit.SensorType
import com.openlattice.chronicle.storage.ChronicleStorage
import com.openlattice.chronicle.survey.SurveySettings
import com.openlattice.chronicle.timeusediary.TimeUseDiarySettings
import org.apache.commons.lang3.StringUtils
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
class Study(
    studyId: UUID = IdConstants.UNINITIALIZED.id,
    title: String,
    description: String = "",
    val createdAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
    val updatedAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
    val startedAt: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC),
    val endedAt: OffsetDateTime = OffsetDateTime.MAX,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val group: String = "",
    val version: String = "",
    val contact: String,
    val organizationIds: Set<UUID> = setOf(),
    val notificationsEnabled: Boolean = false,
    var storage: String = ChronicleStorage.CHRONICLE.id,
    val settings: StudySettings = initialSettings(title),
    val modules: Map<StudyFeature, Any> = mapOf(),
    val phoneNumber: String = "",
) : AbstractSecurableObject(studyId, title, description) {
    companion object {
        /***
         * Only accept certain fields during deserialization.
         */
        @JsonCreator
        @JvmStatic
        fun newStudy(
            title: String,
            description: String = "",
            lat: Double = 0.0,
            lon: Double = 0.0,
            group: String = "",
            version: String = "",
            contact: String,
            organizationIds: Set<UUID> = setOf(),
            notificationsEnabled: Boolean = false,
            storage: String = ChronicleStorage.CHRONICLE.id,
            settings: StudySettings = initialSettings(title),
            modules: Map<StudyFeature, Any> = mapOf(),
            phoneNumber: String = "",
        ): Study = Study(
            title = title,
            description = description,
            lat = lat,
            lon = lon,
            group = group,
            version = version,
            contact = contact,
            organizationIds = organizationIds,
            notificationsEnabled = notificationsEnabled,
            storage = storage,
            settings = settings,
            modules = modules,
            phoneNumber = phoneNumber
        )

        fun initialSettings(title: String, labFriendlyName: String = ""): StudySettings {
            return StudySettings(
                mapOf(
                    StudySettingType.Notifications to StudyNotificationSettings(
                        labFriendlyName = "",
                        studyFriendlyName = title
                    ),
                    StudySettingType.TimeUseDiary to TimeUseDiarySettings(),
                    StudySettingType.Survey to SurveySettings()
                )
            )
        }
    }

    override val category: SecurableObjectType = SecurableObjectType.Study

    init {
        check(storage.length <= 36 && StringUtils.isAlpha(storage)) {
            "Storage name cannot be more 36 characters and must also be alphabetic characters only"
        }
    }

    fun retrieveConfiguredSensors(): Set<SensorType> {
        return (settings[StudySettingType.Sensor] as SensorSetting? ?: SensorSetting(setOf()))
    }

    @JsonIgnore //Required so that jackson will ignore, but still accessible from Hazelcast for indexing.
    fun getNotifyResearchers(): Boolean {
        return (settings[StudySettingType.Notifications] as StudyNotificationSettings? ?: StudyNotificationSettings(
            labFriendlyName = "",
            studyFriendlyName = title
        )).notifyResearchers
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Study) return false
        if (!super.equals(other)) return false

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
        if (modules != other.modules) return false
        if (phoneNumber != other.phoneNumber) return false
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
        result = 31 * result + modules.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String {
        return "Study(createdAt=$createdAt, updatedAt=$updatedAt, startedAt=$startedAt, endedAt=$endedAt, lat=$lat, lon=$lon, group='$group', version='$version', contact='$contact', organizationIds=$organizationIds, notificationsEnabled=$notificationsEnabled, storage='$storage', settings=$settings, modules=$modules, phoneNumber='$phoneNumber', category=$category)"
    }

}


