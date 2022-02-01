package com.openlattice.chronicle.participants

import com.openlattice.chronicle.candidates.Candidate

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Participant(
    val participantId: String,
    val candidate: Candidate
)