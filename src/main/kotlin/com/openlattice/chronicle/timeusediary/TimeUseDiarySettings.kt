package com.openlattice.chronicle.timeusediary

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.study.StudySetting

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
data class TimeUseDiarySettings(
    val enableChangesForSherbrookeUniversity: Boolean = false
) : StudySetting
