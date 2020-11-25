package com.openlattice.chronicle.api;

import com.google.common.base.Optional;
import com.google.common.collect.SetMultimap;
import com.openlattice.chronicle.data.ChronicleAppsUsageDetails;
import com.openlattice.chronicle.data.ChronicleQuestionnaire;
import com.openlattice.chronicle.data.ParticipationStatus;
import com.openlattice.chronicle.sources.Datasource;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
public interface ChronicleApi {
    String SERVICE    = "/chronicle";
    String CONTROLLER = "/v2";
    String BASE       = SERVICE + CONTROLLER;

    String DATASOURCE_ID   = "datasourceId";
    String DATE            = "date";
    String ENTITY_KEY_ID   = "entityKeyId";
    String PARTICIPANT_ID  = "participantId";
    String STUDY_ID        = "studyId";
    String ORGANIZATION_ID = "organizationId";

    String APPS_PATH              = "/apps";
    String EDM_PATH               = "/edm";
    String ENROLL_PATH            = "/enroll";
    String ENROLLMENT_STATUS_PATH = "/status";
    String NOTIFICATIONS_PATH     = "/notifications";
    String QUESTIONNAIRE_PATH     = "/questionnaire";
    String QUESTIONNAIRES_PATH    = "/questionnaires";
    String STATUS_PATH            = "/status";
    String UPLOAD_PATH            = "/upload";

    String DATASOURCE_ID_PATH   = "/{" + DATASOURCE_ID + "}";
    String ENTITY_KEY_ID_PATH   = "/{" + ENTITY_KEY_ID + "}";
    String ORGANIZATION_ID_PATH = "/{" + ORGANIZATION_ID + "}";
    String PARTICIPANT_ID_PATH  = "/{" + PARTICIPANT_ID + "}";
    String STUDY_ID_PATH        = "/{" + STUDY_ID + "}";

    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device,
     * though that may change in the future.
     * <p>
     * Due to privacy changes in Android the device id is not a reliable way of tracking devices.
     * we are leaving the study path in for now, because we don't know that participant's across studies are unique
     *
     * @param organizationId Id of the organization to which study belongs
     * @param studyId        The id of the study with which to enroll the partipant's datasource.
     * @param participantId  The participant id which the device will be associated with.
     * @param datasourceId   A datasource specific id.
     * @param datasource     Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc.
     */

    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH + ENROLL_PATH )
    UUID enroll(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId,
            @Body Optional<Datasource> datasource );

    /**
     * Submit app usage survey.
     *
     * @param organizationId     - Id of the organization to which study belongs
     * @param studyId            - the study id
     * @param participantId      - participantId
     * @param associationDetails - mapping from association EKID to association entity data
     */
    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS_PATH )
    public void submitAppUsageSurvey(
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
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS_PATH )
    List<ChronicleAppsUsageDetails> getParticipantAppsUsageData(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Query( DATE ) String date
    );

    /**
     * Verify that daily push notifications are enabled for participant devices associated with a study
     *
     * @param organizationId - Id of organization to which study belongs
     * @param studyId        - study id
     * @return true if notifications are enabled for a given study
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + NOTIFICATIONS_PATH )
    Boolean isNotificationsEnabled(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Query the enrollment status of a participant
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     * @param participantId  - participantId
     * @return one of { ENROLLED, NOT_ENROLLED, UNKNOWN }
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + ENROLLMENT_STATUS_PATH )
    ParticipationStatus getParticipationStatus(
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
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + QUESTIONNAIRE_PATH + ENTITY_KEY_ID_PATH )
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
    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + QUESTIONNAIRE_PATH )
    void submitQuestionnaire(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Body Map<UUID, Map<FullQualifiedName, Set<Object>>> questionnaireResponses
    );

    /**
     * Get all questionnaires for a given study
     *
     * @param organizationId - Id of the organization to which study belongs
     * @param studyId        - studyId
     * @return a mapping entityKeyId to entity details(name, description, cron etc)
     * or an empty Map if no questionnaires are found.
     */
    @GET( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + QUESTIONNAIRES_PATH )
    Map<UUID, Map<FullQualifiedName, Set<Object>>> getStudyQuestionnaires(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Apps V2: Writes log data for specific studyId and participantId using a specific deviceId to a specific entitySetId.
     *
     * @param organizationId Id of the organization to which study belongs
     * @param studyId        The study id to associate the data with.
     * @param participantId  The participant id to associate the data with.
     * @param datasourceId   The device id logging the data.
     * @param data           The data / entities to write
     * @return The total number of items persisted by the server.
     */
    @POST( BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH + UPLOAD_PATH )
    Integer upload(
            @Path( ORGANIZATION_ID ) UUID organizationId,
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId,
            @Body List<SetMultimap<UUID, Object>> data );

    /**
     * Looks up property type ids for the requested property type FQNS (passed as Strings)
     *
     * @param propertyTypeFqns A set of property type FQNs (as Strings)
     * @return A map from property type FQNs to their corresponding ids
     */
    @POST( BASE + EDM_PATH )
    Map<FullQualifiedName, UUID> getPropertyTypeIds( @Body Set<FullQualifiedName> propertyTypeFqns );

    @GET( BASE + STATUS_PATH )
    Boolean isRunning();
}
