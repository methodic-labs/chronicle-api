package com.openlattice.chronicle.study

import com.openlattice.chronicle.base.OK
import retrofit2.http.*
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
interface StudyComplianceApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/compliance"
        const val BASE = SERVICE + CONTROLLER

        const val STUDY_ID = "studyId"
        const val STUDY = "/study"
        const val NOTIFICATION = "/notifications"


        const val STUDY_ID_PATH = "/{$STUDY_ID}"

    }
    @GET(BASE + STUDY + STUDY_ID_PATH)
    fun getStudyComplianceViolations(@Path(STUDY_ID) studyId: UUID): Map<UUID, Map<String, List<ComplianceViolation>>>

    @POST(BASE + NOTIFICATION)
    fun triggerStudyComplianceNotifications(@Body studyIds: Set<UUID>) : OK

    @GET(BASE + NOTIFICATION)
    fun triggerComplianceNotificationsForAllStudies() : OK
}