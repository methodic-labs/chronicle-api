package com.openlattice.chronicle.candidates

import com.openlattice.chronicle.ids.IdConstants
import java.util.UUID

data class Candidate(
    var candidateId: UUID = IdConstants.UNINITIALIZED.id,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dob: String?
)
