package com.openlattice.chronicle.study

import com.openlattice.chronicle.api.ChronicleApi
import com.openlattice.chronicle.participants.Participant
import com.openlattice.chronicle.sensorkit.SensorDataSample
import com.openlattice.chronicle.sources.SourceDevice
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*


/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
interface StudyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/study"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val PARTICIPANT_ID = "participantId"
        const val DATA_SOURCE_ID = "dataSourceId"

        const val DATA_PATH = "/data"
        const val ENROLL_PATH = "/enroll"
        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"
        const val DATA_SOURCE_ID_PATH = "/{$DATA_SOURCE_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_PATH = "/participant"
        const val ORGANIZATION_PATH = "/organization"
        const val UPLOAD_PATH = "/upload"
        const val SENSOR_PATH = "/sensor"
        const val RETRIEVE = "retrieve"
    }


    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device,
     * though that may change in the future.
     *
     *
     * Due to privacy changes in Android the device id is not a reliable way of tracking devices.
     * we are leaving the study path in for now, because we don't know that participant's across studies are unique
     *
     * @param studyId        The id of the study with which to enroll the partipant's datasource.
     * @param participantId  The participant id which the device will be associated with.
     * @param datasourceId   A datasource specific id.
     * @param datasource     Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc. It is not perfect due to privacy obfuscation implemented by mobile operating systems.
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + DATA_SOURCE_ID_PATH + ENROLL_PATH)
    fun enroll(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Path(DATA_SOURCE_ID) datasourceId: String,
        @Body datasource: SourceDevice
    ): UUID

    /**
     * Creates a new study. A study may be associated with one or more organizations and will be assigned an id
     * at creation time.
     *
     * @param study The study to create.
     * @return The id assigned to the study.
     * etc.
     */
    @POST(BASE)
    fun createStudy(@Body study: Study): UUID

    /**
     * Retrieves an existing study based on id.
     *
     * @param studyId The id of the study to retrieve.
     * @return The study corresponding to the provided id.
     * etc.
     */
    @GET(BASE + STUDY_ID_PATH)
    fun getStudy(@Path(STUDY_ID) studyId: UUID): Study

    /**
     * Retrieves all studies that belong to an organization
     *
     * @param organizationId The id of the organization to retrieve from.
     * @return A list of studies that belong to the provided organization.
     */
    @GET(BASE + ORGANIZATION_PATH + ORGANIZATION_ID_PATH)
    fun getOrgStudies(@Path(ORGANIZATION_ID) organizationId: UUID): List<Study>

    /**
     * Updates an existing study based on id
     * @param studyId The id of the study to update.
     * @param study The changes to the study. Excludes non-user specifiable fields such as studyId, createdAt, updatedAt
     * @param retrieve
     * Does not accept changes to associated organizations.
     */
    @PATCH(BASE + STUDY_ID_PATH)
    fun updateStudy(
        @Path(STUDY_ID) studyId: UUID,
        @Body study: StudyUpdate,
        @Query(RETRIEVE) retrieve: Boolean = false
    ): Study?

    /**
     * Deletes an existing study based on id.
     *
     * @param studyId The id of the study to be destroyed.
     */
    @DELETE(BASE + STUDY_ID_PATH)
    fun destroyStudy(@Path(STUDY_ID) studyId: UUID)

    /**
     * Registers a participant in a study and creates the corresponding candidate if they do not exist.
     * @param participant The participant to register
     * @return The id of the candidate that is created.
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH)
    fun registerParticipant(
        @Path(STUDY_ID) studyId: UUID,
        @Body participant: Participant
    ): UUID

    /**
     * Uploads sensor data from iOS device
     *
     * @param studyId        - studyId
     * @param participantId  - participantId
     * @param datasourceId   - unique Id obtained from https://developer.apple.com/documentation/uikit/uidevice/1620059-identifierforvendor
     * @param data           - A list of SensorDataSample objects.
     * @return number of rows written
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATA_SOURCE_ID_PATH + UPLOAD_PATH + SENSOR_PATH)
    fun uploadSensorData(
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String,
            @Path(DATA_SOURCE_ID) datasourceId: String,
            @Body data: List<SensorDataSample>
    ): Int

    /**
     * Downloads sensor data associated with a participant into a csv file
     *
     * @param studyId - studyId
     * @param participantId - participantId
     * @return A collection of all sensor data associated with participant
     */
    @GET(BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATA_PATH + SENSOR_PATH)
    fun downloadSensorData(
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String
    ): Iterable<Map<String, Any>>
}
