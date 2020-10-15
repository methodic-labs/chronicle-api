package com.openlattice.chronicle;

import com.google.common.base.Optional;
import com.openlattice.chronicle.data.*;
import com.openlattice.chronicle.sources.Datasource;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ChronicleStudyApi {

    String SERVICE    = "/chronicle";
    String CONTROLLER = "/study";
    String BASE       = SERVICE + CONTROLLER;

    String DATASOURCE_ID   = "datasourceId";
    String DATE            = "date";
    String ENTITY_KEY_ID   = "entityKeyId";
    String FILE_TYPE       = "fileType";
    String PARTICIPANT_ID  = "participantId";
    String STUDY_ID        = "studyId";
    String TYPE            = "type";
    String ORGANIZATION_ID   = "organizationId";

    String APPS              = "/apps";
    String AUTHENTICATED     = "/authenticated";
    String DATA_PATH         = "/data";
    String ENROLLMENT_STATUS = "/status";
    String NOTIFICATIONS     = "/notifications";
    String PARTICIPANT_PATH  = "/participant";
    String PREPROCESSED_PATH = "/preprocessed";
    String QUESTIONNAIRE     = "/questionnaire";
    String QUESTIONNAIRES    = "/questionnaires";
    String USAGE_PATH        = "/usage";

    String DATASOURCE_ID_PATH   = "/{" + DATASOURCE_ID + "}";
    String ENTITY_KEY_ID_PATH   = "/{" + ENTITY_KEY_ID + "}";
    String ORGANIZATION_ID_PATH = "/{" + ORGANIZATION_ID + "}";
    String PARTICIPANT_ID_PATH  = "/{" + PARTICIPANT_ID + "}";
    String STUDY_ID_PATH        = "/{" + STUDY_ID + "}";

    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device, though
     * though that may change in the future.
     * <p>
     * Due to privacy changes in Android the device id is not a reliable way of tracking devices.
     * we are leaving the study path in for now, because we don't know that participant's across studies are unique
     *
     * @param studyId        The id of the study with which to enroll the partipant's datasource.
     * @param participantId  The participant id which the device will be associated with.
     * @param datasourceId   A datasource specific id.
     * @param datasource     Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc.
     */
    @Deprecated( since = "apps v2" )
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    UUID enrollSource(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId,
            @Body Optional<Datasource> datasource );

    /**
     * Apps v2: Enrolls a participant's data datasource in a study.
     *
     * @param organizationId Id of the organization to which study belongs
     * @param studyId        The id of the study with which to enroll the partipant's datasource.
     * @param participantId  The participant id which the device will be associated with.
     * @param datasourceId   A datasource specific id.
     * @param datasource     Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc.
     */

    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    UUID enrollDataSourceInOrgStudy(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId,
            @Body Optional<Datasource> datasource );

    /**
     * Verifies that a participant in a study is associated with a specific data source.
     *
     * @param studyId        - the study id
     * @param participantId  - the participant id
     * @param datasourceId   - the datasource (device) id
     * @return Whether or not the datasource is tied to this participant in this study
     */
    //  we are leaving the study path in for now, because we don't know that participant's across studies are unique
    @Deprecated( since = "apps v2" )
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    Boolean isKnownDatasource(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId );

    /**
     * Delete a participant and their data.  Returns the number of entities removed.
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     * @param participantId  - participant id
     */
    @DELETE( BASE + AUTHENTICATED + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    Void deleteParticipantAndAllNeighbors(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Query( TYPE ) DeleteType deleteType
    );

    /**
     * Delete a study and their attached neighbors.  Returns the number of entities removed.
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     */
    @DELETE( BASE + AUTHENTICATED + ORGANIZATION_ID_PATH + STUDY_ID_PATH )
    Void deleteStudyAndAllNeighbors(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Query( TYPE ) DeleteType deleteType
    );

    /**
     * Returns a file download containing all participant data (including neighbor data).
     *
     * @param organizationId         - Id of the organization to which study belongs
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + ORGANIZATION_ID_PATH + STUDY_ID_PATH
            + ENTITY_KEY_ID_PATH )
    Iterable<Map<String, Set<Object>>> getAllParticipantData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Returns a file download containing preprocessed data.
     *
     * @param organizationId         - Id of the organization to which study belongs
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + ORGANIZATION_ID_PATH + STUDY_ID_PATH
            + ENTITY_KEY_ID_PATH + PREPROCESSED_PATH )
    Iterable<Map<String, Set<Object>>> getAllPreprocessedParticipantData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Returns a file download containing user app usage data.
     *
     * @param organizationId         - Id of the organization to which study belongs
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + ORGANIZATION_ID_PATH + STUDY_ID_PATH
            + ENTITY_KEY_ID_PATH + USAGE_PATH )
    Iterable<Map<String, Set<Object>>> getAllParticipantAppsUsageData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Update chronicle_used_by associations when apps usage survey is submitted
     *
     * @param organizationId     - Id of the organization to which study belongs
     * @param studyId            - the study id
     * @param participantId      - participantId
     * @param associationDetails - mapping from association EKID to association entity data
     * @return number of updated associations
     */
    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS )
    public Integer updateAppsUsageAssociationData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Body Map<UUID, Map<FullQualifiedName, Set<Object>>> associationDetails
    );

    /**
     * Get all apps usage data associated with a participant filtered by current date
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - the studyId
     * @param participantId  - the participant
     * @return a list of neighbor entities and associations
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS )
    List<ChronicleAppsUsageDetails> getParticipantAppsUsageData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Query( DATE ) String date
    );

    /**
     * Verify that daily push notifications are enabled for participant devices associated with a study
     *
     * @param studyId - study id
     * @return true if notifications are enabled for a given study
     */
    @Deprecated( since = "apps v2" )
    @GET( BASE + STUDY_ID_PATH + NOTIFICATIONS )
    Boolean isNotificationsEnabled(
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Apps V2: Verify that daily push notifications are enabled for participant devices associated with a study
     *
     * @param organizationId - Id of organization to which study belongs
     * @param studyId        - study id
     * @return true if notifications are enabled for a given study
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + NOTIFICATIONS )
    Boolean isOrgStudyNotificationsEnabled(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Query the enrollment status of a participant
     *
     * @param studyId       - studyId
     * @param participantId - participantId
     * @return one of { ENROLLED, NOT_ENROLLED, UNKNOWN }
     */
    @Deprecated( since = "apps v2" )
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + ENROLLMENT_STATUS )
    ParticipationStatus getParticipationStatus(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId
    );

    /**
     * Apps v2: Query the enrollment status of a participant
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     * @param participantId  - participantId
     * @return one of { ENROLLED, NOT_ENROLLED, UNKNOWN }
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + ENROLLMENT_STATUS )
    ParticipationStatus getOrgStudyParticipationStatus(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId
    );

    /**
     * Retrieve questionnaire matching given entity key id
     *
     * @param organizationId    - Id of the organization to which study belongs
     * @param studyId           - studyId
     * @param questionnaireEKID - questionnaire entity key id
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + QUESTIONNAIRE + ENTITY_KEY_ID_PATH )
    ChronicleQuestionnaire getChronicleQuestionnaire(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID questionnaireEKID
    );

    /**
     * Submit a questionnaire
     *
     * @param organizationId         - Id of the organization to which study belongs
     * @param studyId                - studyId
     * @param participantId          - participant id
     * @param questionnaireResponses mapping from questionEntityKeyId to answer entity
     */
    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + QUESTIONNAIRE )
    void submitQuestionnaire(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Body Map<UUID, Map<FullQualifiedName, Set<Object>>> questionnaireResponses
    );

    /**
     * Get all questionnaires for a given study
     *
     * @param studyId - studyId
     * @return a mapping entityKeyId to entity details(name, description, cron etc)
     * or an empty Map if no questionnaires are found.
     */
    @Deprecated( since = "apps v2" )
    @GET( BASE + STUDY_ID_PATH + QUESTIONNAIRES )
    Map<UUID, Map<FullQualifiedName, Set<Object>>> getStudyQuestionnaires(
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Apps v2: Get all questionnaires for a given study
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     * @return a mapping entityKeyId to entity details(name, description, cron etc)
     * or an empty Map if no questionnaires are found.
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + QUESTIONNAIRES )
    Map<UUID, Map<FullQualifiedName, Set<Object>>> getOrgStudyQuestionnaires(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId
    );
}
