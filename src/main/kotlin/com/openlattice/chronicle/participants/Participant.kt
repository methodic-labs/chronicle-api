package com.openlattice.chronicle.participants

import com.openlattice.chronicle.ids.IdConstants
import java.util.UUID

data class Participant(
    val participantId: UUID = IdConstants.UNINITIALIZED.id,
    val friendlyId: String,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dob: String?
)
