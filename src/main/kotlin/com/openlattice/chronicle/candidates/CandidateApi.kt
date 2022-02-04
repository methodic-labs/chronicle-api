package com.openlattice.chronicle.candidates

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface CandidateApi {

    companion object {
        const val SERVICE = "/chronicle/v3"
        const val CONTROLLER = "/candidates"
        const val BASE = SERVICE + CONTROLLER

        const val BULK_PATH = "/bulk"
        const val CANDIDATE_ID_PARAM = "candidateId"
        const val CANDIDATE_ID_PATH = "/{$CANDIDATE_ID_PARAM}"
    }

    @GET(BASE + CANDIDATE_ID_PATH)
    fun getCandidate(@Path(CANDIDATE_ID_PARAM) candidateId: UUID): Candidate

    @POST(BASE + BULK_PATH)
    fun getCandidates(@Body candidateIds: Set<UUID>): Iterable<Candidate>

    @POST(BASE)
    fun registerCandidate(@Body candidate: Candidate): UUID
}
