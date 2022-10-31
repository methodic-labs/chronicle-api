package com.openlattice.chronicle.timeusediary

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
class TimeUseDiaryColumTitles {
    companion object {
        const val ACTIVITY_COUNTER = "Counter"
        const val ACTIVITY_DURATION = "Duration(Min)"
        const val ACTIVITY_END_TIME = "Activity_End"
        const val ACTIVITY_START_TIME = "Activity_Start"
        const val ADULT_MEDIA = "Adult_Media_Use"
        const val BED_TIME_YESTERDAY = "Bedtime_Yesterday"
        const val BG_AUDIO_DAY = "Background_Audio_Day"
        const val BG_AUDIO_NIGHT = "Background_Audio_Night"
        const val BG_TV_DAY = "Background_TV_Day"
        const val BG_TV_NIGHT = "Background_TV_Night"
        const val CAREGIVER = "Caregiver"
        const val DAY_OF_WEEK = "Day"
        const val DAY_TIME_HOURS = "Daytime_Hours"
        const val FAMILY_ID = "Family_ID"
        const val NON_TYPICAL_DAY_REASON = "Non_Typical_Reason"
        const val NON_TYPICAL_SLEEP_PATTERN = "Non_Typical_Sleep_Pattern"
        const val PARTICIPANT_ID = "Participant_ID"
        const val PRIMARY_ACTIVITY = "Primary_Activity"
        const val PRIMARY_BOOK_TITLE = "Primary_Book_Title"
        const val PRIMARY_BOOK_TYPE = "Primary_Book_Type"
        const val PRIMARY_MEDIA_ACTIVITY ="Primary_Media_Activity"
        const val PRIMARY_MEDIA_AGE = "Primary_Media_Age"
        const val PRIMARY_MEDIA_NAME = "Primary_Media_Name"
        const val SECONDARY_ACTIVITY = "Secondary_Activity"
        const val SECONDARY_BOOK_TITLE ="Secondary_Book_Title"
        const val SECONDARY_BOOK_TYPE = "Secondary_Book_Type"
        const val SECONDARY_MEDIA_ACTIVITY = "Secondary_Media_Activity"
        const val SECONDARY_MEDIA_AGE = "Secondary_Media_Age"
        const val SECONDARY_MEDIA_NAME = "Secondary_Media_Age"
        const val SLEEP_ARRANGEMENT = "Sleeping_Arrangement"
        const val SLEEP_HOURS = "Sleep_Hours"
        const val STUDY_ID = "Study_ID"
        const val SUBMISSION_ID = "Submission_ID"
        const val TIMESTAMP = "Timestamp"
        const val TYPICAL_DAY = "Typical_Day"
        const val SLEEP_PATTERN = "Typical_Sleep_Pattern"
        const val WAKE_UP_COUNT = "Wake_Up_Count"
        const val WAKE_UP_TODAY = "Wakeup_Today"
        const val WAKE_UP_YESTERDAY = "Wakeup_Yesterday"
        const val WAVE_ID = "Wave_ID"
        const val ACTIVITY_DAY = "Activity_Day"
        const val ACTIVITY_DATE = "Activity_Date"
        const val WAKE_UP_TIME_AFTER_ACTIVITY_DAY = "Wake_Up_Time_After_Activity_Day"
        const val BEDTIME_AFTER_ACTIVITY_DAY = "Bedtime_After_Activity_Day"

        val columnTitleToQuestionCodeMap = mapOf(
            FAMILY_ID to TimeUseDiaryQuestionCodes.FAMILY_ID,
            WAVE_ID to TimeUseDiaryQuestionCodes.WAVE_ID,
            DAY_OF_WEEK to TimeUseDiaryQuestionCodes.DAY_OF_WEEK,
            TYPICAL_DAY to TimeUseDiaryQuestionCodes.TYPICAL_DAY,
            NON_TYPICAL_DAY_REASON to TimeUseDiaryQuestionCodes.NON_TYPICAL_DAY_REASON,
            SLEEP_PATTERN to TimeUseDiaryQuestionCodes.SLEEP_PATTERN,
            SLEEP_ARRANGEMENT to TimeUseDiaryQuestionCodes.SLEEP_ARRANGEMENT,
            NON_TYPICAL_SLEEP_PATTERN to TimeUseDiaryQuestionCodes.NON_TYPICAL_SLEEP_PATTERN,
            WAKE_UP_COUNT to TimeUseDiaryQuestionCodes.WAKE_UP_COUNT,
            BG_TV_NIGHT to TimeUseDiaryQuestionCodes.BG_TV_NIGHT,
            BG_AUDIO_NIGHT to TimeUseDiaryQuestionCodes.BG_AUDIO_NIGHT,
            CAREGIVER to TimeUseDiaryQuestionCodes.CAREGIVER,
            PRIMARY_MEDIA_ACTIVITY to TimeUseDiaryQuestionCodes.PRIMARY_MEDIA_ACTIVITY,
            PRIMARY_ACTIVITY to TimeUseDiaryQuestionCodes.PRIMARY_ACTIVITY,
            PRIMARY_MEDIA_AGE to TimeUseDiaryQuestionCodes.PRIMARY_MEDIA_AGE,
            PRIMARY_MEDIA_NAME to TimeUseDiaryQuestionCodes.PRIMARY_MEDIA_NAME,
            PRIMARY_BOOK_TYPE to TimeUseDiaryQuestionCodes.PRIMARY_BOOK_TYPE,
            PRIMARY_BOOK_TITLE to TimeUseDiaryQuestionCodes.PRIMARY_BOOK_TITLE,
            SECONDARY_MEDIA_ACTIVITY to TimeUseDiaryQuestionCodes.SECONDARY_MEDIA_ACTIVITY,
            SECONDARY_MEDIA_AGE to TimeUseDiaryQuestionCodes.SECONDARY_MEDIA_AGE,
            SECONDARY_MEDIA_NAME to TimeUseDiaryQuestionCodes.SECONDARY_MEDIA_NAME,
            SECONDARY_BOOK_TYPE to TimeUseDiaryQuestionCodes.SECONDARY_BOOK_TYPE,
            SECONDARY_BOOK_TITLE to TimeUseDiaryQuestionCodes.SECONDARY_BOOK_TITLE,
            SECONDARY_ACTIVITY to TimeUseDiaryQuestionCodes.SECONDARY_ACTIVITY,
            BG_TV_DAY to TimeUseDiaryQuestionCodes.BG_TV_DAY,
            BG_AUDIO_DAY to TimeUseDiaryQuestionCodes.BG_AUDIO_DAY,
            ADULT_MEDIA to TimeUseDiaryQuestionCodes.ADULT_MEDIA,
            ACTIVITY_DAY to TimeUseDiaryQuestionCodes.ACTIVITY_DAY,
            ACTIVITY_DATE to TimeUseDiaryQuestionCodes.ACTIVITY_DATE,
            WAKE_UP_TIME_AFTER_ACTIVITY_DAY to TimeUseDiaryQuestionCodes.WAKE_UP_TIME_AFTER_ACTIVITY_DAY,
            BEDTIME_AFTER_ACTIVITY_DAY to TimeUseDiaryQuestionCodes.BED_TIME_BEFORE_ACTIVITY_DAY,
            WAKE_UP_TODAY to TimeUseDiaryQuestionCodes.TODAY_WAKEUP_TIME,
        )
    }
}
