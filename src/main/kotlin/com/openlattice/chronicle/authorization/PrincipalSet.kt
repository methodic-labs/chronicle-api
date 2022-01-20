package com.openlattice.chronicle.authorization

data class PrincipalSet(val principalSet: MutableSet<Principal>) : MutableSet<Principal> by principalSet