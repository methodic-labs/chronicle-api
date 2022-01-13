package com.openlattice.chronicle.api

import com.openlattice.chronicle.tud.TimeUseDiaryDownloadDataType
import com.openlattice.chronicle.tud.TimeUseDiaryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
interface TimeUseDiaryApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/time-use-diary"
        const val BASE = SERVICE + CONTROLLER

        const val DATA_TYPE = "dataType"
        const val END_DATE = "endDate"
        const val ORGANIZATION_ID = "organizationId"
        const val PARTICIPANT_ID = "participantId"
        const val START_DATE = "startDate"
        const val STUDY_ID = "studyId"

        const val DOWNLOAD_PATH = "/download"
        const val IDS_PATH = "/ids"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"

        const val STATUS_PATH = "/status"
    }

    // @formatter:off
    /**
     * Record responses of Time Use Diary survey
     *
     * @param organizationId - Organization ID
     * @param studyId - Study ID
     * @param participantId - Participant ID
     * @param responses - a list of objects representing survey responses. Each object has at least 2 keys:
     * questionCode and response to represent a <Question, Answer> pair. Optional startDateTime and endDateTime
     * keys define a time range for some responses
     * @return a unique submission id
     *
     * @sample
     * [
            {
                "questionCode": ["bgTvNight"],
                "response": ["Yes, some of the time"]
            },
            {
                "questionCode": ["secondaryMediaActivity"],
                "response": [
                    "Watched video content (TV, movie, YouTube, etc.)",
                    "Played games (app, console game, etc.)",
                    "Video chat (Facetime, Zoom, etc.)",
                    "Communicated with others in another way (talked on phone, helped to write a text message, etc.)",
                    "Created content (recorded video, took photographs, etc.)"
            ],
                    "startDateTime": ["2021-12-16T18:00:18.422+03:00"],
                    "endDateTime": ["2021-12-16T15:00:18.422+03:00"]
            },
            {
                    "questionCode": ["primaryActivity"],
                    "response": ["Reading or listening to a story (paper book, eBook, audiobook, etc.)"],
                    "startDateTime": ["2021-12-16T19:00:18.422+03:00"],
                    "endDateTime": ["2021-12-16T18:00:18.422+03:00"]
            }
      ]
     */
    // @formatter:on

    @POST(BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun submitTimeUseDiary(
            @Path(ORGANIZATION_ID) organizationId: UUID,
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String,
            @Body responses: List<TimeUseDiaryResponse>
    ): UUID

    /**
     * Returns TUD survey submissionIds grouped by date for a given date range
     *
     * @param organizationId - Organization ID
     * @param studyId - Study ID
     * @param participantId - Participant ID
     * @param startDateTime - lower bound submission date
     * @param endDateTime - upper bound submission date
     * @return A set of submissionIds grouped by Date
     */
    @GET(BASE + IDS_PATH + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun getSubmissionsByDate(
        @Path(ORGANIZATION_ID) organizationId: UUID,
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Query(START_DATE) startDateTime: LocalDateTime,
        @Query(END_DATE) endDateTime: LocalDateTime
    ): Map<LocalDate, Set<UUID>>

    /**
     * Fetches data corresponding to the given submissionIds and writes the result in a csv file
     *
     * @param organizationId - Organization ID
     * @param studyId - Study ID
     * @param participantId - Participant ID
     * @param type - type of data to download
     * @param submissionIds - Ids of survey submission to be downloaded
     */
    @GET(BASE + DOWNLOAD_PATH + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH)
    fun downloadTimeUseDiaryData(
        @Path(ORGANIZATION_ID) organizationId: UUID,
        @Path(STUDY_ID) studyId: UUID,
        @Path(PARTICIPANT_ID) participantId: String,
        @Query(DATA_TYPE) type: TimeUseDiaryDownloadDataType,
        @Body submissionIds: Set<UUID>
    )

    @GET(BASE + STATUS_PATH)
    fun isRunning(): Boolean
}