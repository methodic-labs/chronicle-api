package com.openlattice.chronicle.import

import retrofit2.http.POST

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface ImportApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/import"
        const val BASE = SERVICE + CONTROLLER

        const val STUDIES = "/studies"
        const val PARTICIPANT_STATS = "/participant-stats"
    }

    @POST(BASE + STUDIES)
    fun importStudies(config: ImportStudiesConfiguration)

    @POST(BASE + PARTICIPANT_STATS)
    fun importParticipantStats(config: ImportStudiesConfiguration)
}