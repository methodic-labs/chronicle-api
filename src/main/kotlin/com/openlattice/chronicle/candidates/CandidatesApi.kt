package com.openlattice.chronicle.candidates

import retrofit2.http.POST
import java.util.UUID

interface CandidatesApi {

    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/candidates"
        const val BASE = SERVICE + CONTROLLER
    }

    @POST(BASE)
    fun registerCandidate(candidate: Candidate): UUID
}
