package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A batch of changes to who can access a study, expressed as sets of principal ids -- for users, the Auth0 user id.
 *
 * Revocations are applied before grants, so moving a principal between levels can be expressed as a revoke of the old
 * level plus a grant of the new one in a single request.
 */
data class StudyPermissionsUpdate(
    @JsonProperty("grantViewStudy") val grantViewStudy: Set<String> = setOf(),
    @JsonProperty("grantManageStudy") val grantManageStudy: Set<String> = setOf(),
    @JsonProperty("grantOwnerStudy") val grantOwnerStudy: Set<String> = setOf(),
    @JsonProperty("revokeViewStudy") val revokeViewStudy: Set<String> = setOf(),
    @JsonProperty("revokeManageStudy") val revokeManageStudy: Set<String> = setOf(),
    @JsonProperty("revokeOwnerStudy") val revokeOwnerStudy: Set<String> = setOf(),
)
