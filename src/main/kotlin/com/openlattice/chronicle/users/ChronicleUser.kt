package com.openlattice.chronicle.users

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.authorization.Principal

/**
 * A directory entry for a principal that has, or can be granted, access to a securable object.
 *
 * This is deliberately a narrow projection of the Auth0 user record: it carries what an admin needs in order to decide
 * who they are granting access to, and nothing else. Principals that aren't users (roles, organizations) are still
 * representable -- they simply have no directory details, and a [loginType] of [LoginType.UNKNOWN].
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
data class ChronicleUser(
    @JsonProperty("principal") val principal: Principal,
    @JsonProperty("email") val email: String? = null,
    @JsonProperty("name") val name: String? = null,
    @JsonProperty("picture") val picture: String? = null,
    @JsonProperty("emailVerified") val emailVerified: Boolean = false,
    @JsonProperty("loginType") val loginType: LoginType = LoginType.UNKNOWN,
    @JsonProperty("connections") val connections: List<String> = listOf(),
)
