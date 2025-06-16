package com.openlattice.chronicle.study

import com.openlattice.chronicle.authorization.Principal
import java.util.UUID

data class StudyPermissions(
    val owners: MutableSet<Principal> = mutableSetOf(),
    val managers: MutableSet<Principal> = mutableSetOf(),
    val viewers: MutableSet<Principal> = mutableSetOf(),
)