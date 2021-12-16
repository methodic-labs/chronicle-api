package com.openlattice.chronicle.authorization

import java.util.*

data class SortedPrincipalSet(val principalSet: NavigableSet<Principal>) : NavigableSet<Principal> by principalSet