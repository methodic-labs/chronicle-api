package com.openlattice.chronicle.timeusediary

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
enum class TimeUseDiaryDownloadDataType {
    DAY_TIME {
        override val keywords: List<String>
            get() = listOf(
                "dayOfWeek",
                "typicalDay",
                "nonTypicalDayReason",
                "todayWakeUpTime",
                "dayStartTime",
                "dayEndTime",
                "typicalSleepPattern",
                "nonTypicalSleepPattern",   // Potentially remove
                "nonTypicalSleepReason",
                "sleepArrangement",
                "wakeUpCount",
                "bgTvNight",
                "bgAudioNight"
            )
             },
    NIGHT_TIME {
        override val keywords: List<String>
            get() = listOf(
                "primary",
                "secondary",
                "day",
                "adult",
                "activity",
                "caregiver",
                "typical",
                "counter",
                "duration"
            )
               },
    SUMMARIZED {
        override val keywords: List<String>
            get() = TODO("Not yet implemented")
    };

    abstract val keywords: List<String>
}