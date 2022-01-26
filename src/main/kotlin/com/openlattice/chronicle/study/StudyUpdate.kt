package com.openlattice.chronicle.study

import com.google.common.base.Preconditions
import java.time.OffsetDateTime

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
data class StudyUpdate(
    val title: String?,
    val description: String?,
    val startedAt: OffsetDateTime?,
    val endedAt: OffsetDateTime?,
    val lat: Double?,
    val lon: Double?,
    val group: String?,
    val version: String?,
    val settings: Map<String, Any>?,
    val contact: String?,
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

