package com.openlattice.chronicle;

import retrofit2.http.*;
import java.util.UUID;


public interface ChronicleApi {

    String SERVICE                  = "/server";
    String CONTROLLER               = "/data";
    String BASE                     = SERVICE + CONTROLLER;

    String ID                       = "id";
    String STUDY_ID                 = "studyId";
    String PARTICIPANT_ID           = "participantId";
    String ANDROID_ID                 = "androidId";

    String ID_PATH              = "/{" + ID + "}";
    String STUDY_PATH            = "/{" + STUDY_ID + "}";
    String PARTICIPANT_PATH      = "/{" + PARTICIPANT_ID + "}";

//  sends phone data to openlattice via app, should this be PUT because it'll add not create?
    @POST( BASE + STUDY_PATH + PARTICIPANT_PATH )
    void logData( @Path( STUDY_ID ) String studyId, @Path( PARTICIPANT_ID ) String participantId );

//  creates a study via web
    @POST( BASE + STUDY_PATH )
    UUID createStudy( @Body Study study);

//  creates a participant via web
    @POST( BASE + PARTICIPANT_PATH )
    UUID createParticipant( @Body Person participant);

//  enrolls android device to participant via app
    @POST( BASE + STUDY_PATH + PARTICIPANT_PATH + ANDROID_ID )
    String enrollDevice( @Path( STUDY_ID) UUID studyId,
                       @Path( PARTICIPANT_ID ) UUID participantId,
                       @Path( ANDROID_ID ) String androidId);

//  gets all participants from all studies and non-associated
    @GET( BASE + PARTICIPANT_PATH )
    Iterable<Person> getAllParticipants();

//  gets all participants from a study
    @GET( BASE + STUDY_PATH + PARTICIPANT_PATH)
    Person getParticipantsFromStudy( @Path( PARTICIPANT_ID ) UUID participantId);

//  gets all studies
    @GET( BASE + STUDY_PATH )
    Iterable<Study> getAllStudies();

//  gets a study
    @GET( BASE + STUDY_PATH + ID_PATH)
    Study getStudyById( @Path( STUDY_ID ) UUID studyId );
}
