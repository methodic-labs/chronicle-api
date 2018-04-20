package com.openlattice.chronicle;

import com.google.common.collect.SetMultimap;
import retrofit2.http.*;

import java.util.UUID;

public interface ChronicleApi {

    String SERVICE    = "/server";
    String CONTROLLER = "/data";
    String BASE       = SERVICE + CONTROLLER;

    String STUDY_ID       = "studyId";
    String PARTICIPANT_ID = "participantId";
    String DEVICE_ID      = "deviceId";
    String ENTITY_SET_ID  = "entitySetId";

    String STUDY_ID_PATH       = "/{" + STUDY_ID + "}";
    String PARTICIPANT_ID_PATH = "/{" + PARTICIPANT_ID + "}";
    String DEVICE_ID_PATH      = "/{" + DEVICE_ID + "}";
    String ENTITY_SET_ID_PATH  = "/{" + ENTITY_SET_ID + "}";

    //  writes log data for specific studyId and participantId using a specific deviceId to a specific entitySetId
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DEVICE_ID_PATH + ENTITY_SET_ID_PATH )
    void logData(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) UUID participantId,
            @Path( DEVICE_ID ) String deviceId,
            @Path( ENTITY_SET_ID ) UUID entitySetId,
            @Body SetMultimap<UUID, Object> data );

    //  enrolls android device to participant in a specific study
    //  we are leaving the study path in for now, because we don't know that participant's across studies are unique
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DEVICE_ID_PATH )
    void enrollDevice(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) UUID participantId,
            @Path( DEVICE_ID ) String deviceId );

    //  helper function that verifies the participant (in this study) associates this device
    //  we are leaving the study path in for now, because we don't know that participant's across studies are unique
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DEVICE_ID_PATH )
    Boolean verifyDevice(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) UUID participantId,
            @Path( DEVICE_ID ) String deviceId );

    //  helper function to verify the study associates this participant
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    Boolean verifyParticipant(
            @Path( STUDY_ID ) UUID studyId,
            @Path( PARTICIPANT_ID ) UUID participantId );
}
