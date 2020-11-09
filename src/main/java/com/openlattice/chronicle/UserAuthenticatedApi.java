package com.openlattice.chronicle;

import com.openlattice.chronicle.data.ChronicleDeleteType;
import com.openlattice.chronicle.data.FileType;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
public interface UserAuthenticatedApi {
    String SERVICE    = "/chronicle";
    String CONTROLLER = "/study";
    String BASE       = SERVICE + CONTROLLER;

    String ENTITY_KEY_ID  = "entityKeyId";
    String FILE_TYPE      = "fileType";
    String PARTICIPANT_ID = "participantId";
    String STUDY_ID       = "studyId";
    String TYPE           = "type";

    String AUTHENTICATED     = "/authenticated";
    String DATA_PATH         = "/data";
    String PARTICIPANT_PATH  = "/participant";
    String PREPROCESSED_PATH = "/preprocessed";
    String USAGE_PATH        = "/usage";

    String ENTITY_KEY_ID_PATH  = "/{" + ENTITY_KEY_ID + "}";
    String PARTICIPANT_ID_PATH = "/{" + PARTICIPANT_ID + "}";
    String STUDY_ID_PATH       = "/{" + STUDY_ID + "}";

    /**
     * Delete a participant and their data.  Returns the number of entities removed.
     *
     * @param studyId       - studyId
     * @param participantId - participant id
     */
    @DELETE( BASE + AUTHENTICATED + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    Void deleteParticipantAndAllNeighbors(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) String participantId,
            @Query( TYPE ) ChronicleDeleteType chronicleDeleteType
    );

    /**
     * Delete a study and their attached neighbors.  Returns the number of entities removed.
     *
     * @param studyId - studyId
     */
    @DELETE( BASE + AUTHENTICATED + STUDY_ID_PATH )
    Void deleteStudyAndAllNeighbors(
            @Path( STUDY_ID ) UUID studyId,
            @Query( TYPE ) ChronicleDeleteType chronicleDeleteType
    );

    /**
     * Returns a file download containing all participant data (including neighbor data).
     *
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + ENTITY_KEY_ID_PATH )
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
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + ENTITY_KEY_ID_PATH + PREPROCESSED_PATH )
    Iterable<Map<String, Set<Object>>> getAllPreprocessedParticipantData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );

    /**
     * Returns a file download containing user app usage data.
     *
     * @param studyId                - the study id
     * @param participantEntityKeyId - the participant entity key id
     * @param fileType               - the type of file (csv, json) to return as the download
     * @return All participant data
     */
    @GET( BASE + AUTHENTICATED + PARTICIPANT_PATH + DATA_PATH + STUDY_ID_PATH + ENTITY_KEY_ID_PATH + USAGE_PATH )
    Iterable<Map<String, Set<Object>>> getAllParticipantAppsUsageData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( ENTITY_KEY_ID ) UUID participantEntityKeyId,
            @Query( FILE_TYPE ) FileType fileType
    );
}
