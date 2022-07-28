package com.openlattice.chronicle.android

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
enum class  ChronicleUsageEventType ( val  value: Int) {
    NONE(0x00000000),
    MOVE_TO_FOREGROUND(0x00000001),
    MOVE_TO_BACKGROUND(0x00000002),
    ACTIVITY_PAUSED(MOVE_TO_BACKGROUND.value),
    ACTIVITY_RESUMED(MOVE_TO_FOREGROUND.value),
    CONFIGURATION_CHANGE(0x00000005),
    SHORTCUT_INVOCATION(0x00000008),
    USER_INTERACTION(0x00000007)

}