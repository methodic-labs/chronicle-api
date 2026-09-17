package com.openlattice.chronicle.study

import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.users.ChronicleUser

/**
 * Who has access to a study, bucketed by what that access lets them do.
 *
 * The buckets are derived from the study's acl and are mutually exclusive -- a principal appears exactly once, under
 * the strongest level of access they hold.
 *
 * @param owners Principals with {MATERIALIZE, LINK, READ, WRITE, OWNER, INTEGRATE} -- study admins, who can also
 * manage who else has access.
 * @param managers Principals with {READ, WRITE} -- they can manage the study, but not its access.
 * @param viewers Principals with {READ}.
 */
// No @JsonCreator: every parameter has a default, so Kotlin also emits a no-arg constructor, and annotating the
// primary one leaves Jackson with two conflicting property-based creators. The Kotlin module picks the right one.
data class StudyPermissions(
    @JsonProperty("owners") val owners: Set<ChronicleUser> = setOf(),
    @JsonProperty("managers") val managers: Set<ChronicleUser> = setOf(),
    @JsonProperty("viewers") val viewers: Set<ChronicleUser> = setOf(),
)
