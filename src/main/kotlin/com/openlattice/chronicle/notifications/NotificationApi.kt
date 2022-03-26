package com.openlattice.chronicle.notifications

import com.openlattice.chronicle.base.OK
import retrofit2.http.*
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
        const val PRINCIPAL_ID = "principalId"
        const val PARTICIPANT_ID = "participantId"
        const val DATE = "date"
        const val SETTINGS = "settings"
        const val NOTIFICATION_TYPE = "notificationType"
        const val CONFIRMATION_CODE = "confirmationCode"
        const val PHONE_NUMBER = "phoneNumber"

        const val PHONE_NUMBERS_PATH = "/phone-numbers"
        const val NOTIFICATIONS_PATH = "/notifications"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val PARTICIPANT_ID_PATH = "/{${PARTICIPANT_ID}}"
        const val PRINCIPAL_ID_PATH = "/principal/{$PRINCIPAL_ID}"
        const val NOTIFICATION_TYPE_PATH = "$NOTIFICATIONS_PATH/{$NOTIFICATION_TYPE}"
        const val PHONE_NUMBER_PATH = "$PHONE_NUMBERS_PATH/{$PHONE_NUMBER}"
        const val CONFIRMATION_CODE_PATH = "/confirmation-code/{$CONFIRMATION_CODE}"

        const val MESSAGE_ID = "MessageSid"
        const val MESSAGE_STATUS = "MessageStatus"
        const val STATUS_PATH = "/status"
    }

    /**
     * Gets a researchers currently configured phone number.
     *
     * @param principalId The principal for which to the phone number.
     * @return The principals currently configured phone numbers.
     */
    @GET(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + PHONE_NUMBERS_PATH)
    fun getResearcherPhoneNumber(principalId: String): String


    @PUT(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + PHONE_NUMBER_PATH)
    fun setResearcherPhoneNumber(@Path(PRINCIPAL_ID) principalId: String, @Path(PHONE_NUMBER) phoneNumber: String)

    @POST(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + PHONE_NUMBER_PATH + CONFIRMATION_CODE_PATH)
    fun verifyResearcherPhoneNumber(
        @Path(PHONE_NUMBER) phoneNumber: String,
        @Path(CONFIRMATION_CODE) confirmationCode: String,
    )

    @GET(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + NOTIFICATIONS_PATH)
    fun getResearcherNotificationSettings(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PRINCIPAL_ID) principalId: String,
    ): Map<NotificationType, Set<DeliveryType>>

    @PUT(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + NOTIFICATIONS_PATH)
    fun setResearcherNotificationSettings(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PRINCIPAL_ID) principalId: String,
        @Body settings: Map<NotificationType, Set<DeliveryType>>,
    ): OK

    @GET(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + NOTIFICATION_TYPE_PATH)
    fun getResearcherNotificationSetting(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PRINCIPAL_ID) principalId: String,
        @Path(NOTIFICATION_TYPE) notificationType: NotificationType,
    ): Set<DeliveryType>

    @PUT(BASE + STUDY_ID_PATH + PRINCIPAL_ID_PATH + NOTIFICATION_TYPE_PATH)
    fun setResearcherNotificationSettings(
        @Path(STUDY_ID) studyId: UUID,
        @Path(PRINCIPAL_ID) principalId: String,
        @Path(NOTIFICATION_TYPE) notificationType: NotificationType,
        @Body deliveryTypes: Set<DeliveryType>,
    ): OK

    /**
     * Send Message to participant.
     *
     * @param studyId - Id of the organization to which study belongs
     * @param participantNotificationList - a list of notification details
     */
    @POST(BASE + STUDY_ID_PATH)
    fun sendNotifications(
        @Path(STUDY_ID) studyId: UUID,
        @Body participantNotificationList: List<ParticipantNotification>,
    ): OK

    /**
     * Update staus for messages sent to partipants.
     * @param messageId - String Identifier (SID) - a unique key that is used to identify specific resources.
     * @param messageStatus - Finalized Message Delivery Status
     */
    @POST(BASE + STATUS_PATH)
    fun updateNotificationStatus(
        @Query(MESSAGE_ID) messageId: String,
        @Query(MESSAGE_STATUS) messageStatus: String,
    ): OK

}