package com.openlattice.chronicle.study

import com.google.common.base.Preconditions
import com.openlattice.chronicle.sensorkit.SensorSetting
import java.time.OffsetDateTime

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
data class StudyUpdate(
    val title: String? = null,
    val description: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val group: String? = null,
    val version: String? = null,
    val settings: StudySettings? = null,
    val modules: Map<String, Any>? = null,
    val contact: String? = null,
    val notificationsEnabled: Boolean? = null,
    val storage: String? = null,
) {
    init {
        Preconditions.checkArgument(
            title == null || title.isNotBlank(),
            "Title cannot be blank."
        )
        Preconditions.checkArgument(
            contact == null || contact.isNotBlank(),
            "Contact cannot be blank."
        )
    }
}

