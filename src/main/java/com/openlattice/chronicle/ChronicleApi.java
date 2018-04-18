package com.openlattice.chronicle;
//import com.openlattice.edm.requests.MetadataUpdate;


import retrofit2.http.*;
import java.util.UUID;
import java.util.Set;

// TODO: Create Study object and import Study and Person

public interface ChronicleApi {

    String SERVICE                  = "/server";
    String CONTROLLER               = "/data";
    String BASE                     = SERVICE + CONTROLLER;

    String INSTALL_PATH             = "/install";
    String UPDATE_PATH              = "/update";
    String BULK_PATH                = "/bulk";
    String STUDY_PATH               = "study";
    String PARTICIPANT_PATH         = "participant";

    String STUDY_ID                 = "studyId";
    String PARTICIPANT_ID           = "participantId";
    String DEVICE_ID                 = "deviceId";

    String STUDY_ID_PATH            = "/{" + STUDY_ID + "}";
    String PARTICIPANT_ID_PATH      = "/{" + PARTICIPANT_ID + "}";

//  sends phone data to openlattice, should this be PUT because it'll add not create?
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH )
    void logData( @Path( STUDY_ID ) String studyId, @Path( PARTICIPANT_ID ) String participantId );

//  enrolls android device to participant via app
    @POST( BASE + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DEVICE_ID )
    void enrollDevice( @Path( STUDY_ID) UUID studyId,
                         @Path( PARTICIPANT_ID ) UUID participantId,
                         @Path( DEVICE_ID ) String deviceId);

//  creates a study
    @POST( BASE + STUDY_PATH )
    UUID createStudy( @Body Study study );

//  deletes a study
    @DELETE( BASE + STUDY_ID_PATH)
    void deleteStudy( @Path( STUDY_ID ) UUID studyId );

//  gets all studies
    @GET( BASE + STUDY_PATH )
    Iterable<Study> getAllStudies();

//  gets a study
    @GET( BASE + STUDY_PATH + STUDY_ID_PATH )
    Study getStudyById( @Path( STUDY_ID ) UUID studyId );

//  creates a participant
    @POST( BASE + PARTICIPANT_PATH )
    UUID createParticipant( @Body Person participant );

//  deletes a participant
    @DELETE( BASE + PARTICIPANT_ID_PATH)
    void deleteParticipant( @Path( PARTICIPANT_ID ) UUID participantId );

//  gets all participants from all studies and non-associated
    @GET( BASE + PARTICIPANT_PATH )
    Iterable<Person> getAllParticipants();

//  gets all participants from a study
    @GET( BASE + STUDY_ID_PATH + PARTICIPANT_PATH )
    Iterable<Person> getParticipantsFromStudy( @Path( STUDY_ID ) UUID studyId );

//  gets participant by id
    @GET( BASE + PARTICIPANT_ID_PATH)
    Person getParticipantById( @Path( PARTICIPANT_ID ) UUID participantId );

//  updates a participant's metadata
    @PATCH( BASE + UPDATE_PATH + PARTICIPANT_ID_PATH)
    void updateParticipantMetadata( @Path( PARTICIPANT_ID ) UUID participantId,
                                    @Body MetadataUpdate metadataUpdate );

//  updates a study's metadata
    @PATCH( BASE + UPDATE_PATH + STUDY_ID_PATH)
    void updateStudyMetadata( @Path( STUDY_ID ) UUID studyId,
                              @Body MetadataUpdate metadataUpdate );

//  adds a participant to a study
    @POST( BASE + INSTALL_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    void addParticipantToStudy( @Path( STUDY_ID )UUID studyID,
                                @Path( PARTICIPANT_ID ) UUID participantId );

//  removes a participant from a study
    @DELETE( BASE + INSTALL_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    void removeParticipantFromStudy( @Path( STUDY_ID )UUID studyID,
                                     @Path( PARTICIPANT_ID ) UUID participantId );

//  adds bulk of participants to a study
    @POST( BASE + INSTALL_PATH + STUDY_ID_PATH + BULK_PATH)
    void addParticipantsToStudy( @Path( STUDY_ID )UUID studyID,
                                 @Body Set<UUID> participantIds );

//  removes bulk of participants from a study
    @DELETE( BASE + INSTALL_PATH + STUDY_ID_PATH + BULK_PATH)
    void removeParticipantsFromStudy( @Path( STUDY_ID )UUID studyID,
                                      @Body Set<UUID> participantIds );
}
