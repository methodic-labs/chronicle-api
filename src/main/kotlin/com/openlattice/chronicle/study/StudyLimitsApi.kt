package com.openlattice.chronicle.study

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface StudyLimitsApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/limits"
        const val BASE = SERVICE + CONTROLLER

        const val STUDY_ID = "studyId"
        const val STUDY = "/study"

        const val STUDY_ID_PATH = "/{$STUDY_ID}"

    }

    @PUT(BASE + STUDY + STUDY_ID_PATH)
    fun setStudyLimits(@Path(STUDY_ID) studyId: UUID, @Body studyLimits: StudyLimits)

    @GET(BASE + STUDY + STUDY_ID_PATH)
    fun getStudyLimits(@Path(STUDY_ID) studyId: UUID): StudyLimits
}