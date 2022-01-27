package com.openlattice.chronicle.people

import com.openlattice.chronicle.ids.IdConstants
import java.util.UUID

data class Person(
    var personId: UUID = IdConstants.UNINITIALIZED.id,
    val friendlyId: String,
    val firstName: String?,
    val lastName: String?,
    val name: String?,
    val dob: String?
)
