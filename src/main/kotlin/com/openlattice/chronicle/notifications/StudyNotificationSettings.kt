package com.openlattice.chronicle.notifications

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.study.StudyDuration
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
    val researcherPhoneNumber: String = "",
    val notifyResearchers: Boolean = false,
    val notifyOnEnrollment: Boolean = false,
    val noDataUploaded: StudyDuration = StudyDuration(days = 1),
    val noTudSubmitted: StudyDuration = StudyDuration(days = 1),
    val noAppUsageSurveySubmitted: StudyDuration = StudyDuration(days = 1)
) : StudySetting {

    @JsonIgnore
    fun getEnrollmentMessage(): String {
        return if (labFriendlyName.isBlank()) {
            "You've been invited to enroll in $studyFriendlyName. "
        } else {
            "You've been invited by $labFriendlyName to enroll in the $studyFriendlyName"
        }
    }
}
