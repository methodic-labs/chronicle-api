package com.openlattice.chronicle.study


import com.google.common.collect.SetMultimap
import com.openlattice.chronicle.base.OK
import com.openlattice.chronicle.organizations.ChronicleDataCollectionSettings
import com.openlattice.chronicle.participants.Participant
import com.openlattice.chronicle.participants.ParticipantStats
import com.openlattice.chronicle.sensorkit.SensorDataSample
import com.openlattice.chronicle.sensorkit.SensorType
import com.openlattice.chronicle.sources.SourceDevice
import retrofit2.http.*
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
        const val SOURCE_DEVICE_ID = "sourceDeviceId"

        const val VERIFY_PATH = "/verify"
        const val DATA_PATH = "/data"
        const val ENROLL_PATH = "/enroll"
        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"
        const val SOURCE_DEVICE_ID_PATH = "/{$SOURCE_DEVICE_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_PATH = "/participant"
        const val PARTICIPANTS_PATH = "/participants"
        const val ORGANIZATION_PATH = "/organization"
        const val UPLOAD_PATH = "/upload"
        const val SENSORS_PATH = "/sensors"
        const val SETTINGS_PATH = "/settings"
        const val STATS_PATH = "/stats"
        const val IOS_PATH = "/ios"
        const val ANDROID_PATH = "/android"
        const val RETRIEVE = "retrieve"
        const val DATA_COLLECTION = "/data-collection/"
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
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + SOURCE_DEVICE_ID_PATH + ENROLL_PATH)
    fun enroll(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Path(SOURCE_DEVICE_ID) datasourceId: String,
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
     * @param retrieve Set to true to retrieve the updated view of the study.
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
     * @return The id of the background job created to delete usage data related to the study
     */
    @DELETE(BASE + STUDY_ID_PATH)
    fun destroyStudy(@Path(STUDY_ID) studyId: UUID): Iterable<UUID>

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
     * @param studyId The id of the study.
     * @param participantId The id of the participant.
     * @param sourceDeviceId A unique id obtained from https://developer.apple.com/documentation/uikit/uidevice/1620059-identifierforvendor
     * @param data A list of SensorDataSample objects.
     * @return number of rows written
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + IOS_PATH + SOURCE_DEVICE_ID_PATH)
    fun uploadSensorData(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Path(SOURCE_DEVICE_ID) sourceDeviceId: String,
        @Body data: List<SensorDataSample>
    ): Int

    /**
     * Uploads sensor data from iOS device
     *
     * @param studyId - studyId
     * @param dataCollectionSettings - A list of SensorDataSample objects.
     * @return number of rows written
     */
    @PUT(BASE + STUDY_ID_PATH + DATA_COLLECTION)
    fun setChronicleDataCollectionSettings(
        @Path(STUDY_ID) studyId: UUID,
        @Body dataCollectionSettings: ChronicleDataCollectionSettings
    ): OK

    /**
     * Returns the settings for a given study
     * This endpoint expects the caller to know the value type(s)
     * @param studyId studyId
     */
    @GET(BASE + STUDY_ID_PATH + SETTINGS_PATH)
    fun getStudySettings(
        @Path(STUDY_ID) studyId: UUID
    ): Map<String, Any>

    /**
     * Fetches sensors configured for a study.
     *
     * @param studyId studyId
     * @return all sensor types for given study
     */
    @GET(BASE + STUDY_ID_PATH + SETTINGS_PATH + SENSORS_PATH)
    fun getStudySensors(
        @Path(STUDY_ID) studyId: UUID
    ): Set<SensorType>


    /** Upload usage event data from android devices
     * @param studyId studyId
     * @param participantId participantId
     * @param datasourceId device id unique to each combination of app-signing key, user and device
     * @param data A list of usage event objects to write. Each object encapsulates an instance of
     * android's UsageEvents.Event with properties such as package name, timestamp and event type
     * ref: https://developer.android.com/reference/android/app/usage/UsageEvents.Event
     */
    @POST(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + ANDROID_PATH + SOURCE_DEVICE_ID_PATH)
    fun uploadAndroidUsageEventData(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Path(SOURCE_DEVICE_ID) datasourceId: String,
        @Body data: List<SetMultimap<UUID, Any>>
    ): Int

    @GET(BASE + STUDY_ID_PATH + PARTICIPANTS_PATH)
    fun getStudyParticipants(@Path(STUDY_ID) studyId: UUID): Iterable<Participant>

    /**
     * Verifies that participant is in a study
     * @param studyId studyId
     * @param participantId participantId
     * @return true if the participant is in the study
     */
    @GET(BASE + STUDY_ID_PATH + PARTICIPANT_PATH + PARTICIPANT_ID_PATH + VERIFY_PATH)
    fun isKnownParticipant(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String
    ): Boolean
    
    @GET(BASE)
    fun getAllStudies() : Iterable<Study>

    /**
     * Retrieves stats of participant in a study
     * @param studyId studyId
     * @return a map of participantId to stats object.
     */
    @GET(BASE + STUDY_ID_PATH + PARTICIPANTS_PATH + STATS_PATH)
    fun getParticipantStats(
        @Path(STUDY_ID) studyId: UUID
    ): Map<String, ParticipantStats>
}
