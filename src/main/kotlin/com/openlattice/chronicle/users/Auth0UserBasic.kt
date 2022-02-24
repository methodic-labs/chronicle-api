/*
 * Copyright (C) 2018. OpenLattice, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * You can contact the owner of the copyright at support@openlattice.com
 */
package com.openlattice.chronicle.users

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

data class Auth0UserBasic @JsonIgnoreProperties(ignoreUnknown = true) constructor(
    @JsonProperty("user_id") val userId: String?,
    @JsonProperty("email") val email: String?,
    @JsonProperty("nickname") val nickname: String?,
    @JsonProperty("username") var username: String? = email ?: nickname ?: userId,
    @JsonProperty("app_metadata") val appMetadata: Map<String, Any>,
    val roles: MutableSet<String> = appMetadata["roles"] as MutableSet<String>?
        ?: mutableSetOf(),
    val organizations: MutableSet<String> = appMetadata["organizations"] as MutableSet<String>?
        ?: mutableSetOf(),
    val loadTime: OffsetDateTime? = OffsetDateTime.now()
)
