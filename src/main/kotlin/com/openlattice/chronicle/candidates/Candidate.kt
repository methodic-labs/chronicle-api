package com.openlattice.chronicle.candidates

import com.openlattice.chronicle.authorization.AbstractSecurableObject
import com.openlattice.chronicle.authorization.SecurableObjectType
import com.openlattice.chronicle.ids.IdConstants
import java.time.LocalDate
import java.util.UUID

data class Candidate(
    var candidateId: UUID = IdConstants.UNINITIALIZED.id,
    val firstName: String? = null,
    val lastName: String? = null,
    val name: String? = null,
    val dateOfBirth: LocalDate? = null,
    val email: String? = null,
    val phoneNumber: String? = null
) : AbstractSecurableObject(
    // title and description are intentionally ignored
    candidateId, "candidate", "candidate"
) {
    override val category: SecurableObjectType = SecurableObjectType.Candidate
}
