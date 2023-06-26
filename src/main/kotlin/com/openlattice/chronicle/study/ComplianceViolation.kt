package com.openlattice.chronicle.study

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
data class ComplianceViolation(
    val reason: ViolationReason,
    val description: String
)

enum class ViolationReason {
    NO_DATA_UPLOADED,
    NO_RECENT_DATA_UPLOADED
}

