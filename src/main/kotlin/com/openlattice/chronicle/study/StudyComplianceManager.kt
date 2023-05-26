package com.openlattice.chronicle.study

import java.util.*

interface StudyComplianceManager {
    fun getNonCompliantStudies( studies: Collection<UUID>) : Map<UUID, Map<String, List<ComplianceViolation>>>
    fun getAllNonCompliantStudies() : Map<UUID, Map<String, List<ComplianceViolation>>>
}
