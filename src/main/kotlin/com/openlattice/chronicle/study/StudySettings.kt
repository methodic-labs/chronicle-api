package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonCreator

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
class StudySettings @JsonCreator constructor( settings: Map<StudySettingType, StudySetting> = mapOf()) :
    Map<StudySettingType, StudySetting> by settings {
    private val h = settings.hashCode()
    override fun equals(other: Any?): Boolean {
        return if (other !is StudySettings) return false
        else {
            if (other.keys == this.keys) {
                this.all { other.getValue(it.key) == it.value }
            } else false
        }
    }

    override fun hashCode(): Int = h
}

enum class StudySettingType {
    DataCollection,
    Notifications,
    Sensor,
    TimeUseDiary,
    Survey,
}
