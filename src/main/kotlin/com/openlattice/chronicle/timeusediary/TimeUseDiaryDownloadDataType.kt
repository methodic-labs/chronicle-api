package com.openlattice.chronicle.timeusediary

import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.ACTIVITY_COUNTER
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.ACTIVITY_DURATION
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.ACTIVITY_END_TIME
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.ACTIVITY_START_TIME
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.ADULT_MEDIA
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.BED_TIME_YESTERDAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.BG_AUDIO_DAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.BG_AUDIO_NIGHT
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.BG_TV_DAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.BG_TV_NIGHT
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.CAREGIVER
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.DAY_OF_WEEK
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.DAY_TIME_HOURS
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.FAMILY_ID
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.NON_TYPICAL_DAY_REASON
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.NON_TYPICAL_SLEEP_PATTERN
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PARTICIPANT_ID
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_ACTIVITY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_BOOK_TITLE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_BOOK_TYPE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_MEDIA_ACTIVITY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_MEDIA_AGE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.PRIMARY_MEDIA_NAME
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_ACTIVITY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_BOOK_TITLE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_BOOK_TYPE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_MEDIA_ACTIVITY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_MEDIA_AGE
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SECONDARY_MEDIA_NAME
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SLEEP_ARRANGEMENT
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SLEEP_HOURS
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.SLEEP_PATTERN
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.STUDY_ID
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.TIMESTAMP
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.TYPICAL_DAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.WAKE_UP_COUNT
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.WAKE_UP_TODAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.WAKE_UP_YESTERDAY
import com.openlattice.chronicle.timeusediary.TimeUseDiaryColumTitles.Companion.WAVE_ID

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
enum class TimeUseDiaryDownloadDataType {
    DayTime {
        override val downloadColumnTitles: LinkedHashSet<String>
            get() = linkedSetOf(
                STUDY_ID,
                PARTICIPANT_ID,
                FAMILY_ID,
                WAVE_ID,
                TIMESTAMP,
                DAY_OF_WEEK,
                TYPICAL_DAY,
                NON_TYPICAL_DAY_REASON,
                ACTIVITY_COUNTER,
                PRIMARY_ACTIVITY,
                ACTIVITY_START_TIME,
                ACTIVITY_END_TIME,
                ACTIVITY_DURATION,
                CAREGIVER,
                PRIMARY_MEDIA_ACTIVITY,
                PRIMARY_MEDIA_AGE,
                PRIMARY_MEDIA_NAME,
                PRIMARY_BOOK_TYPE,
                PRIMARY_BOOK_TITLE,
                SECONDARY_MEDIA_ACTIVITY,
                SECONDARY_MEDIA_AGE,
                SECONDARY_MEDIA_NAME,
                SECONDARY_BOOK_TYPE,
                SECONDARY_BOOK_TITLE,
                SECONDARY_ACTIVITY,
                BG_TV_DAY,
                BG_AUDIO_DAY,
                ADULT_MEDIA
            )
    },
    NightTime {
        override val downloadColumnTitles: LinkedHashSet<String>
            get() = linkedSetOf(
                STUDY_ID,
                PARTICIPANT_ID,
                FAMILY_ID,
                WAVE_ID,
                TIMESTAMP,
                DAY_OF_WEEK,
                TYPICAL_DAY,
                NON_TYPICAL_DAY_REASON,
                WAKE_UP_YESTERDAY,
                BED_TIME_YESTERDAY,
                WAKE_UP_TODAY,
                DAY_TIME_HOURS,
                SLEEP_HOURS,
                SLEEP_PATTERN,
                NON_TYPICAL_SLEEP_PATTERN,
                SLEEP_ARRANGEMENT,
                WAKE_UP_COUNT,
                BG_TV_NIGHT,
                BG_AUDIO_NIGHT
            )
    },
    Summarized {
        override val downloadColumnTitles: LinkedHashSet<String>
            get() = TODO("Not yet implemented")
    };

    abstract val downloadColumnTitles: LinkedHashSet<String>
}