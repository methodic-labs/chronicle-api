package com.openlattice.chronicle.study

import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 *
 * This class describes the default limits (quotas) placed on a study.
 *
 * @param studyDuration How long a study is allowed to run for
 * @param dataRetentionDuration The amount of time after a study ends that data will be retained by the platform.
 * @param capacty The number of participants allowed in a study.
 * @param features The modules enabled for this study.
 *
 */
data class StudyLimits(
    val studyDuration: StudyDuration = StudyDuration(years = 1),
    val dataRetentionDuration: StudyDuration = StudyDuration(days = 90),
    val participantLimit: Int = 25,
    val features: EnumSet<StudyFeature> = EnumSet.of(
        StudyFeature.CHRONICLE,
        StudyFeature.CHRONICLE_DATA_COLLECTION,
        StudyFeature.CHRONICLE_SURVEYS
    ),
)