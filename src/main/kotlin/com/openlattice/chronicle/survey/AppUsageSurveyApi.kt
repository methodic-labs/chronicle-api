package com.openlattice.chronicle.survey

import com.openlattice.chronicle.api.ChronicleApi
import com.openlattice.chronicle.data.ChronicleAppsUsageDetails
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
interface AppUsageSurveyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/appUsage"
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
     * Get app usage data for the specified participantId filtered by current date
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - the studyId
     * @param participantId  - the participant
     * @param date  - usage date
     * @return a list of AppUsage objects
     */
    @GET(BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun getAppUsageData(
            @Path(ORGANIZATION_ID) organizationId: UUID,
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String,
            @Query(DATE) date: String
    ): List<AppUsage>

    /**
     * Submit app usage survey responses for the specified participantId
     *
     * @param organizationId - organizationId
     * @param studyId - studyId
     * @param participantId - participantId
     * @param surveyResponses - mapping from appUsageId to a set of users
     */
    @POST(BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun submitAppUsageSurvey(
            @Path(ORGANIZATION_ID) organizationId: UUID,
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String,
            @Body surveyResponses: Map<UUID, Set<String>>
    )
}