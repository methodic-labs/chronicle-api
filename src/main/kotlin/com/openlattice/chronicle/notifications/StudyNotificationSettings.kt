package com.openlattice.chronicle.notifications

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.study.StudySetting

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 *
 * This class holds default settings for sending participant notifications.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
data class StudyNotificationSettings(
    val labFriendlyName: String,
    val studyFriendlyName: String,
    val notifyOnEnrollment: Boolean = false,
) : StudySetting {

    @JsonIgnore
    fun getEnrollmentMessage(): String {
        return if (labFriendlyName.isBlank()) {
            "You've been invited to enroll to "
        } else {
            "You've been invited by $labFriendlyName to enroll in the $studyFriendlyName"
        }
    }
}
