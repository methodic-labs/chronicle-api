package com.openlattice.chronicle.study

import com.openlattice.chronicle.api.ChronicleApi
import com.openlattice.chronicle.participants.Participant
import com.openlattice.chronicle.sources.SourceDevice
import retrofit2.http.*
import java.util.*


/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
interface StudyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/study"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val PARTICIPANT_ID = "participantId"
        const val DATA_SOURCE_ID = "dataSourceId"


        const val ENROLL_PATH = "/enroll"
        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"
        const val DATA_SOURCE_ID_PATH = "/{$DATA_SOURCE_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_PATH = "/participant"
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
     * Updates an existing study based on id
     * @param studyId The id of the study to update.
     * @param study The changes to the study. Excludes non-user specifiable fields such as studyId, createdAt, updatedAt
     * Does not accept changes to associated organizations.
     */
    @PATCH(BASE + STUDY_ID_PATH)
    fun updateStudy(
        @Path(STUDY_ID) studyId: UUID,
        @Body study: StudyUpdate
    )

    /**
     * Deletes an existing study based on id.
     *
     * @param studyId The id of the study to be destroyed.
     * @return An integer corresponding to the number of destroyed studies
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
}
