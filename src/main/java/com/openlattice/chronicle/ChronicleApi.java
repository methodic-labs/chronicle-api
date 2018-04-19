package com.openlattice.chronicle;

import retrofit2.http.*;
import java.util.UUID;

public interface ChronicleApi {

    String SERVICE                  = "/server";
    String CONTROLLER               = "/data";
    String BASE                     = SERVICE + CONTROLLER;

    String STUDY_ID                 = "studyId";
    String PARTICIPANT_ID           = "participantId";
    String DEVICE_ID                 = "deviceId";

    String STUDY_ID_PATH            = "/{" + STUDY_ID + "}";
    String PARTICIPANT_ID_PATH      = "/{" + PARTICIPANT_ID + "}";

//  sends phone data to openlattice, should this be PUT because it'll add not create?
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    void logData( @Path( STUDY_ID ) UUID studyId, @Path( PARTICIPANT_ID ) UUID participantId );

//  enrolls android device to participant via app
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DEVICE_ID )
    void enrollDevice( @Path( STUDY_ID) UUID studyId,
                         @Path( PARTICIPANT_ID ) UUID participantId,
                         @Path( DEVICE_ID ) String deviceId);
}
