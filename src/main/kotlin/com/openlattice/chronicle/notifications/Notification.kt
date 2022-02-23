package com.openlattice.chronicle.notifications

import java.time.OffsetDateTime
import java.util.*

/**
 * @author Todd Bergman <todd@openlattice.com>
 */

data class Notification(
    val id: UUID,
    val candidateId: UUID,
    val organizationId: UUID,
    val studyId: UUID,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime = OffsetDateTime.now(),
    var status: String,
    var messageId: String,
    val type: String,
    val body: String,
    val email: String?,
    val phone: String,
)