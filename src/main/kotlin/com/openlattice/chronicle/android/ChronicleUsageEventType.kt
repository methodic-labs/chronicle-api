package com.openlattice.chronicle.android

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
enum class  ChronicleUsageEventType ( val  value: Int) {
    MOVE_TO_BACKGROUND(0x00000002),
    MOVE_TO_FOREGROUND(0x00000001),
    ACTIVITY_PAUSED(MOVE_TO_BACKGROUND.value),
    ACTIVITY_RESUMED(MOVE_TO_FOREGROUND.value),


}