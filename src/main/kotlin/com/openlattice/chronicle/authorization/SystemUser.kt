package com.openlattice.chronicle.authorization

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
enum class SystemUser(principalId: String) {
    METHODIC("Methodic");

    val principal : Principal = Principal( PrincipalType.USER, principalId)
}