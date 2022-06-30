package com.openlattice.chronicle.survey

import com.openlattice.chronicle.base.OK
import com.openlattice.chronicle.data.FileType
import retrofit2.http.*
import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
interface SurveyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/survey"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val PARTICIPANT_ID = "participantId"
        const val DATE = "date"
        const val START_DATE = "startDate"
        const val END_DATE = "endDate"
        const val QUESTIONNAIRE_ID = "questionnaireId"
        const val TYPE = "type"
        const val FILE_NAME = "fileName"

        const val PARTICIPANT_PATH = "/participant"
        const val APP_USAGE_PATH = "/app-usage"
        const val QUESTIONNAIRE_PATH = "/questionnaire"
        const val FILTERED_PATH = "/filtered"
        const val DATA_PATH = "/data"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"
        const val QUESTIONNAIRE_ID_PATH = "/{$QUESTIONNAIRE_ID}"
    }

    /**
     * Retrieve the app package filtered from the app usage survey.
     *
     * @param studyId Identifier of the study whose app usage survey filter is being retrieved.
     * @return A list of app package names that are filtered from the app usage survey.
     */
    @GET(BASE + STUDY_ID_PATH + FILTERED_PATH)
    fun getAppsFilteredForStudyAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
    ): List<String>

    /**
     * Set the entire app usage survey filter all at once.
     *
     * @param studyId Identifier of the study whose app usage survey filter is being modified.
     * @param filteredAppPackages A set of app package names to filter from the app usage survey
     * @return HTTP OK with message success if request succeeds, other error code otherwise.
     */
    @PUT(BASE + STUDY_ID_PATH + FILTERED_PATH)
    fun setAppsFilteredForStudyAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
        filteredAppPackages: Set<String>,
    ): OK

    /**
     * Filter a single app package name from the filter list.
     *
     * @param studyId Identifier of the study whose app usage survey filter is being modified.
     * @param filteredAppPackage An app package name to filter from the app usage survey.
     * @return HTTP OK with message success if request succeeds, other error code otherwise.
     */
    @PATCH(BASE + STUDY_ID_PATH + FILTERED_PATH)
    fun filterAppForStudyAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
        filteredAppPackage: String,
    ): OK

    /**
     * Allow a single app for the study app usage survey.
     *
     * @param studyId Identifier of the study whose app usage survey filter is being modified.
     * @return HTTP OK with message success if request succeeds, other error code otherwise.
     */
    @HTTP(method = "DELETE", path = BASE + STUDY_ID_PATH + FILTERED_PATH)
    fun allowAppForStudyAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
        filteredAppPackage: String,
    ): OK

    /**
     * Queries the chronicle_usage_events table for usage events matching given studyId, participantId and date
     *
     * @param studyId the studyId
     * @param participantId the participant
     * @param startDateTime lower bound date (inclusive)
     * @param endDateTime upper bound date (exclusive)
     * @return a list of AppUsage objects where each object encapsulates
     * an app used at a specific timestamp in a specific timezone
     */
    @GET(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + APP_USAGE_PATH)
    fun getAppUsageSurveyData(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Query(START_DATE) startDateTime: OffsetDateTime,
        @Query(END_DATE) endDateTime: OffsetDateTime,
    ): List<AppUsage>

    /**
     * Submit app usage survey responses for the specified participantId
     * Note that each instance of AppUsage(appPackageName, appLabel, timestamp) is considered a unique entity
     * and will not be overwritten if it already exists in storage
     *
     * @param studyId studyId
     * @param participantId participantId
     * @param surveyResponses a list of AppUsage Objects
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + APP_USAGE_PATH)
    fun submitAppUsageSurvey(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Body surveyResponses: List<AppUsage>,
    )

    /**
     * Create a new questionnaire
     *
     * @param studyId
     * @return questionnaire Id
     */
    @POST(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH)
    fun createQuestionnaire(
        @Path(STUDY_ID) studyId: UUID,
        @Body questionnaire: Questionnaire,
    ): UUID

    /**
     * Deletes a questionnaire. This does not delete any existing responses for the questionnaire
     *
     * @param studyId studyId
     * @param questionnaireId questionnaire id
     * @return success message if successful
     */
    @DELETE(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH + QUESTIONNAIRE_ID_PATH)
    fun deleteQuestionnaire(
        @Path(STUDY_ID) studyId: UUID,
        @Path(QUESTIONNAIRE_ID) questionnaireId: UUID,
    ): OK

    /**
     * Retrieves a questionnaire of given id
     *
     * @param studyId studyId
     * @param questionnaireId questionnaire id
     */
    @GET(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH + QUESTIONNAIRE_ID_PATH)
    fun getQuestionnaire(
        @Path(STUDY_ID) studyId: UUID,
        @Path(QUESTIONNAIRE_ID) questionnaireId: UUID,
    ): Questionnaire

    /**
     * Updates questionnaire details
     * @param studyId studyId
     * @param questionnaireId questionnaire id
     * @return "SUCCESS" if operation was successful
     */
    @PATCH(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH + QUESTIONNAIRE_ID_PATH)
    fun updateQuestionnaire(
        @Path(STUDY_ID) studyId: UUID,
        @Path(QUESTIONNAIRE_ID) questionnaireId: UUID,
        @Body update: QuestionnaireUpdate,
    ): OK

    /**
     * Retrieves all questionnaires associated with a study
     *
     * @param studyId studyId
     * @return a list of questionnaire objects
     */
    @GET(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH)
    fun getStudyQuestionnaires(
        @Path(STUDY_ID) studyId: UUID,
    ): List<Questionnaire>

    /**
     * Submit a participant's questionnaire responses
     *
     * @param studyId studyId
     * @param participantId participantId
     * @param questionnaireId questionnaire id
     * @return "success" message if submission was successful
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + QUESTIONNAIRE_PATH + QUESTIONNAIRE_ID_PATH)
    fun submitQuestionnaireResponses(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Path(QUESTIONNAIRE_ID) questionnaireId: UUID,
        @Body responses: List<QuestionnaireResponse>,
    ): OK

    /**
     * Fetches all responses to a given questionnaire and writes to specified file type
     *
     * @param studyId studyId
     * @param questionnaireId questionnaire Id
     * @param fileType type of file to write to
     */
    @GET(BASE + STUDY_ID_PATH + QUESTIONNAIRE_PATH + QUESTIONNAIRE_ID_PATH + DATA_PATH)
    fun getQuestionnaireResponses(
        @Path(STUDY_ID) studyId: UUID,
        @Path(QUESTIONNAIRE_ID) questionnaireId: UUID,
        @Query(value = TYPE) fileType: FileType,
    ): Iterable<Map<String, Any>>
}
