package com.openlattice.chronicle

import com.openlattice.chronicle.authorization.Principal
import com.openlattice.chronicle.authorization.PrincipalType
import com.openlattice.chronicle.study.StudyPermissions
import com.openlattice.chronicle.users.ChronicleUser
import com.openlattice.chronicle.users.LoginType

class StudyPermissionsJacksonSerializerTest : AbstractJacksonSerializationTest<StudyPermissions>() {
    override val sampleData: StudyPermissions
        get() = StudyPermissions(
            owners = setOf(
                ChronicleUser(
                    principal = Principal(PrincipalType.USER, "auth0|1234"),
                    email = "jane@example.com",
                    name = "Jane Doe",
                    picture = "https://example.com/jane.png",
                    emailVerified = true,
                    loginType = LoginType.USERNAME_PASSWORD,
                    connections = listOf("Username-Password-Authentication"),
                )
            ),
            managers = setOf(
                ChronicleUser(
                    principal = Principal(PrincipalType.USER, "google-oauth2|5678"),
                    email = "john@example.com",
                    loginType = LoginType.OAUTH,
                    connections = listOf("google-oauth2"),
                )
            ),
            // A principal with no directory entry still round trips -- it just carries no details.
            viewers = setOf(ChronicleUser(Principal(PrincipalType.ROLE, "auth0|role"))),
        )

    override val clazz: Class<StudyPermissions>?
        get() = StudyPermissions::class.java

    override fun logResult(result: SerializationResult<StudyPermissions>) {
        logger.info(result.jsonString)
    }
}
