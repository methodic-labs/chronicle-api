package com.openlattice.chronicle.participants

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.candidates.Candidate
import com.openlattice.chronicle.data.ParticipationStatus
import com.openlattice.chronicle.util.JsonFields

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Participant(
    @JsonProperty(JsonFields.PARTICIPANT_ID) val participantId: String,
    @JsonProperty(JsonFields.CANDIDATE) val candidate: Candidate,
    @JsonProperty(JsonFields.PARTICIPATION_STATUS) val participationStatus: ParticipationStatus
)