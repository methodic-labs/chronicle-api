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
package com.openlattice.chronicle.authorization

import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
enum class SystemRole(principalId: String) {
    ADMIN("admin"),
    USER("user"),
    ANONYMOUS_USER("AnonymousUser"),
    AUTHENTICATED_USER("AuthenticatedUser"),
    OPENLATTICE("openlatticeRole");
    val principal : Principal = Principal( PrincipalType.ROLE, principalId)


    companion object {
        val adminRole: Principal = ADMIN.principal
        private var allRoles: Set<String> = values().map { obj: SystemRole -> obj.getName() }.toSet()

        operator fun contains(role: String): Boolean {
            return allRoles.contains(role)
        }

        fun valuesAsArray(): Array<String> {
            return allRoles.toTypedArray()
        }

    }

    fun getName() : String = principal.id
}