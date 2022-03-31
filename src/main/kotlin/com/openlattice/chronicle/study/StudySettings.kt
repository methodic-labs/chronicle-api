package com.openlattice.chronicle.study

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
class StudySettings(settings : Map<String, StudySetting> = mapOf()) : Map<String, StudySetting>  by settings {
    private val h = settings.hashCode()
    override fun equals(other: Any?): Boolean {
        return if (other !is StudySettings) return false
        else {
            if( other.keys == this.keys ) {
                this.all { other.getValue(it.key) == it.value }
            } else false
        }
    }

    override fun hashCode(): Int = h
}

enum class StudySettingType(val key:String) {
    DATA_COLLECTION("dataCollection"),
    SENSOR("sensor"),
    NOTIFICATIONS("notifications")
}