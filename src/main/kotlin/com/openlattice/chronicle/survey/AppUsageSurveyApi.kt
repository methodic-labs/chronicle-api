package com.openlattice.chronicle.survey

import retrofit2.http.*
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
interface AppUsageSurveyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/app-usage"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val PARTICIPANT_ID = "participantId"
        const val DATE = "date"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_ID_PATH = "/{${PARTICIPANT_ID}}"
    }

    /**
     * Queries the chronicle_usage_events table for usage events matching given studyId, participantId and date
     *
     * @param studyId        - the studyId
     * @param participantId  - the participant
     * @param date  - usage date
     * @return a list of AppUsage objects where each object encapsulates
     * an app used at a specific timestamp in a specific timezone
     */
    @GET(BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun getAppUsageData(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Query(DATE) date: String
    ): List<AppUsage>

    /**
     * Submit app usage survey responses for the specified participantId
     *
     * @param studyId - studyId
     * @param participantId - participantId
     * @param surveyResponses - a list of AppUsage Objects
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun submitAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Body surveyResponses: List<AppUsage>
    )
}
