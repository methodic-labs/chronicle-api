package com.openlattice.chronicle.participants

import java.time.OffsetDateTime

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
data class ParticipantStats(
    val participantId: String,
    val androidFirstDate: OffsetDateTime? = null,
    val androidLastDate: OffsetDateTime? = null,
    val androidDatesCount: Int = 0,
    val iosFirstDate: OffsetDateTime? = null,
    val iosLastDate: OffsetDateTime? = null,
    val iosDatesCount: Int = 0,
    val tudFirsDate: OffsetDateTime? = null,
    val tudLastDate: OffsetDateTime? = null,
    val tudDatesCount: Int = 0
)
