package com.openlattice.chronicle

import com.openlattice.chronicle.study.StudyPermissionsUpdate

class StudyPermissionsUpdateJacksonSerializerTest : AbstractJacksonSerializationTest<StudyPermissionsUpdate>() {
    override val sampleData: StudyPermissionsUpdate
        get() = StudyPermissionsUpdate(
            grantOwnerStudy = setOf("auth0|1234"),
            grantManageStudy = setOf("google-oauth2|5678"),
            // How the UI expresses "put this person at exactly this level": revoke everything, then grant.
            revokeViewStudy = setOf("auth0|1234", "google-oauth2|5678"),
        )

    override val clazz: Class<StudyPermissionsUpdate>?
        get() = StudyPermissionsUpdate::class.java

    override fun logResult(result: SerializationResult<StudyPermissionsUpdate>) {
        logger.info(result.jsonString)
    }
}
