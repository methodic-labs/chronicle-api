package com.openlattice.chronicle.api.v3

import com.openlattice.chronicle.study.Study
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
interface StudiesApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val VERSION = "/v3"
        const val CONTROLLER = "/studies"
        const val BASE = SERVICE + VERSION + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
    }

    @POST(BASE + ORGANIZATION_ID_PATH)
    fun submitStudy(
        @Path(ORGANIZATION_ID) organizationId: UUID,
        @Body study: Study
    ): UUID
}
