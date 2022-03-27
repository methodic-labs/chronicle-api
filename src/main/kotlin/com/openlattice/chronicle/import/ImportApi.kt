package com.openlattice.chronicle.import

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface ImportApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/import"
        const val BASE = SERVICE + CONTROLLER

        const val STUDIES = "/studies"
        const val PARTICIPANT_STATS = "/participant-stats"
        const val APP_USAGE_SURVEY = "/app-usage-survey"
        const val SYSTEM_APPS ="/system-apps"
        const val TIME_USE_DIARY = "/time-use-diary"
        const val PERMISSIONS = "/permissions"
        const val PARTICIPANTS = "/participants"
    }

    @PATCH(BASE + STUDIES )
    fun initializeStudySettings() : Int

    @POST(BASE + STUDIES)
    fun importStudies(@Body config: ImportStudiesConfiguration)

    @POST(BASE + PARTICIPANT_STATS)
    fun importParticipantStats(@Body config: ImportStudiesConfiguration)

    @POST(BASE + APP_USAGE_SURVEY)
    fun importAppUsageSurvey(@Body config: ImportStudiesConfiguration)

    @POST(BASE + SYSTEM_APPS)
    fun importSystemApps(@Body config: ImportStudiesConfiguration)

    @POST(BASE + TIME_USE_DIARY)
    fun importTimeUseDiarySubmissions(@Body config: ImportStudiesConfiguration)

    @POST(BASE + PERMISSIONS)
    fun importUserPermissions(@Body config: ImportStudiesConfiguration)

    @POST(BASE + PARTICIPANTS)
    fun importParticipants(@Body config: ImportStudiesConfiguration)
}
