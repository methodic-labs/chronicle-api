package com.openlattice.chronicle.notifications

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 *
 * This class holds default settings for sending participant notifications.
 */
data class StudyNotificationSettings (
    val labFriendlyName: String,
    val studyFriendlyName: String,
    val notifyOnEnrollment: Boolean = false
) {

    fun getEnrollmentMessage(): String {
        return if( labFriendlyName.isBlank() ) {
          "You've been invited to enroll to "
        } else {
            "You've been invited by $labFriendlyName to enroll in the $studyFriendlyName"
        }
    }

    companion object {
        const val SETTINGS_KEY = "notifications"
    }
}
