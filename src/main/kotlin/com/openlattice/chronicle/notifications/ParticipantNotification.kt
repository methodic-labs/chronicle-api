package com.openlattice.chronicle.notifications

import java.time.OffsetDateTime
import java.util.*

/**
 * @author Todd Bergman <todd@openlattice.com>
 */

data class ParticipantNotification(
    val participantId: String,
    val notificationType: NotificationType,
    val deliveryType: EnumSet<DeliveryType>,
    val message: String,
    val dateTime: OffsetDateTime = OffsetDateTime.now(),
)
