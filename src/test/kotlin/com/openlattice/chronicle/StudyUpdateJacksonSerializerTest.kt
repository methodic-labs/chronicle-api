package com.openlattice.chronicle

import com.openlattice.chronicle.study.StudyUpdate

/**
 * Guards against regressions in StudyUpdate's Jackson serialization. Notably,
 * annotating the constructor of an all-default-param Kotlin data class with
 * @JsonCreator causes Jackson to see two property-based creators (the explicit
 * one and the Kotlin-synthesized 0-arg) and fail at request-time with
 * "Conflicting property-based creators".
 */
class StudyUpdateJacksonSerializerTest : AbstractJacksonSerializationTest<StudyUpdate>() {
    override val sampleData: StudyUpdate
        get() = StudyUpdate(
            title = "This is a test study.",
            contact = "tester@openlattice.com",
            description = "for serialization regression testing",
            group = "group",
            version = "v1",
            notificationsEnabled = true,
        )
    override val clazz: Class<StudyUpdate>?
        get() = StudyUpdate::class.java

    override fun logResult(result: SerializationResult<StudyUpdate>) {
        logger.info(result.jsonString)
    }
}
