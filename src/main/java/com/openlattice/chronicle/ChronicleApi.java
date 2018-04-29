package com.openlattice.chronicle;

import com.google.common.collect.SetMultimap;
import java.util.List;
import retrofit2.http.*;

import java.util.UUID;

public interface ChronicleApi {

    String SERVICE    = "/chronicle";
    String CONTROLLER = "/data";
    String BASE       = SERVICE + CONTROLLER;

    String STUDY_ID       = "studyId";
    String PARTICIPANT_ID = "participantId";
    String DATASOURCE_ID  = "datasourceId";
    String ENTITY_SET_ID  = "entitySetId";

    String STUDY_ID_PATH       = "/{" + STUDY_ID + "}";
    String PARTICIPANT_ID_PATH = "/{" + PARTICIPANT_ID + "}";
    String DATASOURCE_ID_PATH  = "/{" + DATASOURCE_ID + "}";
    String ENTITY_SET_ID_PATH  = "/{" + ENTITY_SET_ID + "}";


    /**
     * Writes log data for specific studyId and participantId using a specific deviceId to a specific entitySetId.
     *
     * @param studyId The study id to associate the data with.
     * @param participantId The participant id to associate the data with.
     * @param deviceId The device id logging the data.
     * @param data
     * @return The total number of items persisted by the server.
     *
     */
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    Integer upload(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) UUID participantId,
            @Path( DATASOURCE_ID ) String deviceId,
            @Body List<SetMultimap<UUID, Object>> data );


}
