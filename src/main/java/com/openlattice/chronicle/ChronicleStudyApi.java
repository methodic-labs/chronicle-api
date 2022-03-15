package com.openlattice.chronicle;

import com.google.common.base.Optional;
import com.openlattice.chronicle.data.ChronicleAppsUsageDetails;
import com.openlattice.chronicle.data.LegacyChronicleQuestionnaire;
import com.openlattice.chronicle.data.ParticipationStatus;
import com.openlattice.chronicle.sources.SourceDevice;
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

    String DATASOURCE_ID  = "datasourceId";
    String DATE           = "date";
    String ENTITY_KEY_ID  = "entityKeyId";
    String PARTICIPANT_ID = "participantId";
    String STUDY_ID       = "studyId";

    String APPS              = "/apps";
    String DATA_PATH         = "/data";
    String ENROLLMENT_STATUS = "/status";
    String NOTIFICATIONS     = "/notifications";
    String PARTICIPANT_PATH  = "/participant";
    String QUESTIONNAIRE     = "/questionnaire";
    String QUESTIONNAIRES    = "/questionnaires";
    String TIME_USE_DIARY    = "/time-use-diary";

    String DATASOURCE_ID_PATH  = "/{" + DATASOURCE_ID + "}";
    String ENTITY_KEY_ID_PATH  = "/{" + ENTITY_KEY_ID + "}";
    String PARTICIPANT_ID_PATH = "/{" + PARTICIPANT_ID + "}";
    String STUDY_ID_PATH       = "/{" + STUDY_ID + "}";

    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device, though
     * though that may change in the future.
     * <P>
     * Due to privacy changes in Android the device id is not a reliable way of tracking devices.
     * we are leaving the study path in for now, because we don't know that participant's across studies are unique
     *
     * @param studyId       The id of the study with which to enroll the partipant's datasource.
     * @param participantId The participant id which the device will be associated with.
     * @param datasourceId  A datasource specific id.
     * @param datasource    Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc.
     */
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    UUID enrollSource(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId,
            @Body Optional<SourceDevice> datasource );

    /**
     * Verifies that a participant in a study is associated with a specific data source.
     *
     * @param studyId       - the study id
     * @param participantId - the participant id
     * @param datasourceId  - the datasource (device) id
     * @return Whether or not the datasource is tied to this participant in this study
     */
    //  we are leaving the study path in for now, because we don't know that participant's across studies are unique
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    Boolean isKnownDatasource(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Path( DATASOURCE_ID ) String datasourceId );

    /**
     * Verify that daily push notifications are enabled for participant devices associated with a study
     *
     * @param studyId - study id
     * @return true if notifications are enabled for a given study
     */
    @GET( BASE + STUDY_ID_PATH + NOTIFICATIONS )
    Boolean isNotificationsEnabled(
            @Path( STUDY_ID ) UUID studyId
    );

    /**
     * Query the enrollment status of a participant
     *
     * @param studyId       - studyId
     * @param participantId - participantId
     * @return one of { ENROLLED, NOT_ENROLLED, UNKNOWN }
     */
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + ENROLLMENT_STATUS )
    ParticipationStatus getParticipationStatus(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId
    );

    /**
     * Get all questionnaires for a given study
     *
     * @param studyId - studyId
     * @return a mapping entityKeyId to entity details(name, description, cron etc)
     * or an empty Map if no questionnaires are found.
     */
    @GET( BASE + STUDY_ID_PATH + QUESTIONNAIRES )
    Map<UUID, Map<FullQualifiedName, Set<Object>>> getStudyQuestionnaires(
            @Path( STUDY_ID ) UUID studyId
    );
}
