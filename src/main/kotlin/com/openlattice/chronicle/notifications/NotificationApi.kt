package com.openlattice.chronicle.notifications

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

/**
 * @author Todd Bergman <todd@openlattice.com>
 */

interface NotificationApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/notification"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val PARTICIPANT_ID = "participantId"
        const val DATE = "date"

        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_ID_PATH = "/{${PARTICIPANT_ID}}"

        const val MESSAGE_ID = "MessageSid"
        const val MESSAGE_STATUS = "MessageStatus"
        const val STATUS_PATH = "/status"
    }

    /**
     * Send Message to participant.
     *
     * @param studyId - Id of the organization to which study belongs
     * @param participantNotificationList - a list of notification details
     */
    @POST(BASE + STUDY_ID_PATH)
    fun sendNotifications(
        @Path( STUDY_ID ) studyId :UUID,
        @Body participantNotificationList: List<ParticipantNotification>
    )

    /**
     * Update staus for messages sent to partipants.
     * @param messageId - String Identifier (SID) - a unique key that is used to identify specific resources.
     * @param messageStatus - Finalized Message Delivery Status
     */
    @POST(BASE + STATUS_PATH)
    fun updateNotificationStatus(
        @Query(MESSAGE_ID) messageId: String,
        @Query(MESSAGE_STATUS) messageStatus: String
    )

}