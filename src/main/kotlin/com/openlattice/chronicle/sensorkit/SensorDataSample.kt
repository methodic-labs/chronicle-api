package com.openlattice.chronicle.sensorkit

import java.time.OffsetDateTime
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 * An instance of this class encapsulates a sample recorded by SensorKit framework
 */

data class SensorDataSample(
        val id: UUID,
        val dateRecorded: OffsetDateTime,
        val duration: Double,
        val data: String,
        val device: String,
        val timezone: String,
        val sensor: SensorType,
        val startDate: OffsetDateTime,
        val endDate: OffsetDateTime
)

data class SensorSourceDevice(
        val model: String,
        val name: String,
        val systemName: String,
        val systemVersion: String
)

data class AppUsage(
        val usageTime: Double,
        val textInputSessions: Map<String, Double>,
        val bundleIdentifier: String
)

data class NotificationUsage(
        val bundleIdentifier: String,
        val event: String
)

data class DeviceUsageData(
        val totalScreenWakes: Int,
        val totalUnlocks: Int,
        val totalUnlockDuration: Double,
        val appUsage: Map<String, List<AppUsage>>,
        val webUsage: Map<String, Double>,
        val notificationUsage: Map<String, NotificationUsage>
)

data class PhoneUsageData(
        val totalIncomingCalls: Int,
        val totalOutgoingCalls: Int,
        val totalPhoneDuration: Double,
        val totalUniqueContacts: Int
)

data class MessagesUsageData(
        val totalIncomingMessages: Int,
        val totalOutgoingMessages: Int,
        val totalUniqueContacts: Int
)

//NOTE: optional parameters are currently only available for iOS 15.0+
data class KeyboardMetricsData(
        val totalWords: Int,
        val totalAlteredWords: Int,
        val totalTaps: Int,
        val totalDrags: Int,
        val totalDeletes: Int,
        val totalEmojis: Int,
        val totalPaths: Int,
        val totalPathTime: Double,
        val totalPathLength: Double,
        val totalAutoCorrections: Int,
        val totalSpaceCorrections: Int,
        val totalRetroCorrections: Int,
        val totalTranspositionCorrections: Int,
        val totalInsertKeyCorrections: Int,
        val totalSkipTouchCorrections: Int,
        val totalNearKeyCorrections: Int,
        val totalSubstitutionCorrections: Int,
        val totalHitTestCorrections: Int,
        val totalTypingDuration: Double,
        val totalPathPauses: Int?,
        val totalPauses: Int?,
        val totalTypingEpisodes: Int?,
        val pathTypingSpeed: Double?, // QuickWords per minute,
        val typingSpeed: Double?, // typing rate in characters per second,
        val emojiCountBySentiment: Map<String, Int>,
        val wordCountBySentiment: Map<String, Int>
)
