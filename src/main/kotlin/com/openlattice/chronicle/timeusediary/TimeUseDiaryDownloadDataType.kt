package com.openlattice.chronicle.timeusediary

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
enum class TimeUseDiaryDownloadDataType {
    DAY_TIME {
        override val downloadColumnTitles: List<String>
            get() = listOf(
                "primaryActivity",
                "careGiver",
                "primaryBookType",
                "bgTvDay",
                "bgAudioDay",
                "adultMedia",
                "secondaryBookType",
                "secondaryMediaActivity",
                "secondaryActivity",
                "secondaryMediaAge",
                "secondaryMediaName",
                "primaryMediaActivity",
                "primaryMediaAge",
                "primaryMediaName",
                "dayOfWeek",
                "typicalDay",
                "nonTypicalDayReason",
                "todayWakeUpTime",
                "dayStartTime",
                "dayEndTime",
                "typicalSleepPattern",
                "nonTypicalSleepPattern",
                "nonTypicalSleepReason",
                "sleepArrangement",
                "wakeUpCount",
                "bgTvNight",
                "bgAudioNight",
                "startDateTime",
                "endDateTime"
            )
             },
    NIGHT_TIME {
        override val downloadColumnTitles: List<String>
            get() = listOf(
                "dayOfWeek",
                "typicalDay",
                "nonTypicalDayReason",
                "todayWakeUpTime",
                "dayEndTime",
                "typicalSleepPattern",
                "nonTypicalSleepReason",
                "sleepArrangement",
                "wakeUpCount",
                "bgTvNight",
                "bgAudioNight"
            )
               },
    SUMMARIZED {
        override val downloadColumnTitles: List<String>
            get() = TODO("Not yet implemented")
    };

    abstract val downloadColumnTitles: List<String>
}