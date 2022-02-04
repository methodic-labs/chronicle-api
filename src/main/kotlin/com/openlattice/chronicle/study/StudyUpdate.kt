package com.openlattice.chronicle.study

import com.google.common.base.Preconditions
import java.time.OffsetDateTime

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
data class StudyUpdate(
    val title: String? = null,
    val description: String? = null,
    val startedAt: OffsetDateTime? = null,
    val endedAt: OffsetDateTime? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val group: String? = null,
    val version: String? = null,
    val settings: Map<String, Any>? = null,
    val contact: String? = null ,
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

