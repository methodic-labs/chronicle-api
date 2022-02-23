package com.openlattice.chronicle.notifications

import java.time.OffsetDateTime
import java.util.*

/**
 * @author Todd Bergman <todd@openlattice.com>
 */

data class NotificationDetails(
    val notificationType: String,
    val dateTime: OffsetDateTime = OffsetDateTime.now(),
    val candidateId: UUID,
    val phoneNumber: String,
    val url: String,
    val studyId: UUID
)
