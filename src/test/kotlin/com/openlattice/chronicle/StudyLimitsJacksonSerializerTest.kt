package com.openlattice.chronicle

import com.openlattice.chronicle.study.StudyLimits

class StudyLimitsJacksonSerializerTest : AbstractJacksonSerializationTest<StudyLimits>() {
    override val sampleData: StudyLimits
        get() = StudyLimits()
    override val clazz: Class<StudyLimits>?
        get() = StudyLimits::class.java
    override fun logResult(result: SerializationResult<StudyLimits>) {
        logger.info(result.jsonString)
    }
}
