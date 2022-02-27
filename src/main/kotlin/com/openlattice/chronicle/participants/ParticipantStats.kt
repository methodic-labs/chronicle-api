package com.openlattice.chronicle.participants

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class ParticipantStats(
    val studyId: UUID,
    val participantId: String,
    val androidFirstDate: OffsetDateTime? = null,
    val androidLastDate: OffsetDateTime? = null,
    val androidDatesCount: Int = 0,
    val iosFirstDate: OffsetDateTime? = null,
    val iosLastDate: OffsetDateTime? = null,
    val iosDatesCount: Int = 0,
    val tudFirstDate: OffsetDateTime? = null,
    val tudLastDate: OffsetDateTime? = null,
    val tudDatesCount: Int = 0
)
