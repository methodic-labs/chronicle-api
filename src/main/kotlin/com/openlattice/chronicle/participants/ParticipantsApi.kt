package com.openlattice.chronicle.participants

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface ParticipantsApi {

    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/participants"
        const val BASE = SERVICE + CONTROLLER

        const val STUDY_ID_PARAM = "studyId"
        const val STUDY_ID_PATH = "/{${STUDY_ID_PARAM}}"
    }

    @POST(BASE + STUDY_ID_PATH)
    fun addStudyParticipant(@Path(STUDY_ID_PARAM) studyId: UUID, @Body participant: Participant)
}
