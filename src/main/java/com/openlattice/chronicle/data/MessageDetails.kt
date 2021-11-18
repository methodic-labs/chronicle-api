package com.openlattice.chronicle.data

import java.time.OffsetDateTime
import java.util.*

data class MessageDetails(
    val messageType: MessageType,
    val dateTime: OffsetDateTime,
    val participantId: String,
    val phoneNumber: String,
    val url: String,
    val studyId: UUID
)
