package com.openlattice.chronicle.study

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT
import java.util.UUID

/**
 * @author Solomon Tang <solomon@openlattice.com>
 */
interface StudyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/study"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
    }

    /**
     * Creates a new study. A study may be associated with one or more organizations and will be assigned an id
     * at creation time.
     *
     * @param study The study to create.
     * @return The id assigned to the study.
     * etc.
     */
    @POST(BASE)
    fun createStudy(@Body study: Study): UUID

    /**
     * Retrieves an existing study based on id.
     *
     * @param studyId The id of the study to retrieve.
     * @return The study corresponding to the provided id.
     * etc.
     */
    @GET(BASE + STUDY_ID_PATH)
    fun getStudy(@Path(STUDY_ID) studyId: UUID): Study

    /**
     * Updates an existing study based on id
     */
    @PUT(BASE + STUDY_ID_PATH)
    fun updateStudy(
        @Path(STUDY_ID) studyId: UUID,
        @Body study: Study
    )
}
