package com.openlattice.chronicle;

 import retrofit2.http.*;

public interface ChronicleApi {

    String SERVICE                  = "/server";
    String CONTROLLER               = "/data";
    String BASE                     = SERVICE + CONTROLLER;

    String STUDY_ID                 = "studyId";
    String PARTICIPANT_ID           = "participantId";

    String STUDY_ID_PATH            = "/{" + STUDY_ID + "}";
    String PARTICIPANT_ID_PATH      = "/{" + PARTICIPANT_ID + "}";

    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    void logData( @Path( STUDY_ID ) String studyId, @Path( PARTICIPANT_ID ) String participantId );
}
