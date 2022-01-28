package com.openlattice.chronicle.candidates

import com.openlattice.chronicle.ids.IdConstants
import java.time.LocalDate
import java.util.UUID

data class Candidate(
    var id: UUID = IdConstants.UNINITIALIZED.id,
    val firstName: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val dateOfBirth: LocalDate? = null,
    val email: String? = null,
    val phoneNumber: String? = null
)
