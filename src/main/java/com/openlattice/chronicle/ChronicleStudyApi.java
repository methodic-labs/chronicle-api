package com.openlattice.chronicle;

import com.google.common.base.Optional;
import com.google.common.collect.SetMultimap;
import com.openlattice.chronicle.data.FileType;
import com.openlattice.chronicle.sources.Datasource;
import org.springframework.web.bind.annotation.RequestBody;
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
    String ENTITY_KEY_ID  = "entityKeyId";
    String ENTITY_SET_ID  = "entitySetId";
    String FILE_TYPE      = "fileType";
    String PARTICIPANT_ID = "participantId";
    String STUDY_ID       = "studyId";

    String DATA_PATH         = "/data";
    String PARTICIPANT_PATH  = "/participant";
    String PREPROCESSED_PATH = "/preprocessed";
    String APPS              = "/apps";

    String DATASOURCE_ID_PATH  = "/{" + DATASOURCE_ID + "}";
    String ENTITY_KEY_ID_PATH  = "/{" + ENTITY_KEY_ID + "}";
    String ENTITY_SET_ID_PATH  = "/{" + ENTITY_SET_ID + "}";
    String PARTICIPANT_ID_PATH = "/{" + PARTICIPANT_ID + "}";
    String STUDY_ID_PATH       = "/{" + STUDY_ID + "}";

    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device, though
     * though that may change in the future.
     * <p>
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
            @Body Optional<Datasource> datasource );

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
     * Verify that a participants is part of this study.
     *
     * @param studyId       - the study id
     * @param participantId - the participant id
     * @return Whether or not this participant is in this study
     */
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    Boolean isKnownParticipant(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId );

    /**
     * Returns a file download containing all participant data (including neighbor data).
     *
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + ENTITY_KEY_ID_PATH )
    Iterable<Map<String, Set<Object>>> getAllParticipantData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Returns a file download containing preprocessed data.
     *
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + ENTITY_KEY_ID_PATH + PREPROCESSED_PATH )
    Iterable<Map<String, Set<Object>>> getAllPreprocessedParticipantData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Update chronicle_used_by associations when apps usage survey is submitted
     *
     * @param studyId            - the study id
     * @param participantId      - participantId
     * @param associationDetails - mapping from association EKID to association entity data
     * @return number of updated associations
     */
    @POST( BASE + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS )
    public Integer updateAppsUsageAssociationData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @RequestBody Map<UUID, Map<UUID, Set<Object>>> associationDetails
    );

    /**
     * Get all apps usage data associated with a participant filtered by current date
     *
     * @param studyId       - the studyId
     * @param participantId - the participant
     * @return a list of neighbor entities and associations
     */
    @GET( BASE + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + APPS )
    List<SetMultimap<UUID, Object>> getParticipantAppsUsageData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId
    );
}
