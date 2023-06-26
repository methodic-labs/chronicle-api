package com.openlattice.chronicle.android

import java.time.OffsetDateTime
import java.util.*

/**
 * * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 *
 * The bina parameters for this query are in the following order:
 * @param studyId (text/uuid)
 * @param participantId (text)
 * @param appPackageName (text)
 * @param interactionType (text)
 * @param timestamp (timestamptz)
 * @param timezone (text)
 * @param user (text)
 * @param applicationLabel (text)
 */
data class ChronicleUsageEvent(
    val studyId: UUID,
    val participantId: String,
    val appPackageName: String,
    val interactionType: String,
    val eventType: Int = fromInteractionType(interactionType) ,
    val timestamp: OffsetDateTime,
    val timezone: String,
    val user: String,
    val applicationLabel: String,
) : ChronicleSample

fun fromInteractionType(interactionType: String): Int {
    return when (interactionType) {
        "Move to Background" -> ChronicleUsageEventType.MOVE_TO_BACKGROUND.value
        "Move to Foreground" -> ChronicleUsageEventType.MOVE_TO_FOREGROUND.value
        "Configuration Change" -> ChronicleUsageEventType.CONFIGURATION_CHANGE.value
        "Shortcut Invocation" -> ChronicleUsageEventType.SHORTCUT_INVOCATION.value
        "User Interaction" -> ChronicleUsageEventType.USER_INTERACTION.value
        "None" -> ChronicleUsageEventType.NONE.value
        "Usage Stat" -> -1
        else -> {
            interactionType.substringAfter("Unknown importance: ").toInt()
        }
    }
}