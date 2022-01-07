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

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.Objects
import com.openlattice.chronicle.util.JsonFields
import java.io.Serializable

/**
 * This class represents a principal in the OpenLattice system. It is only serializable because it is used
 * as an internal member of [SystemRole] enum members.
 */
data class Principal(
    @JsonProperty(JsonFields.TYPE_FIELD) val type: PrincipalType,
    @JsonProperty(JsonFields.ID_FIELD) val id: String
) : Comparable<Principal>, Serializable {

    @Transient
    private var h = 0

    override fun compareTo(o: Principal): Int {
        var result = type.compareTo(o.type)
        if (result == 0) {
            result = id.compareTo(o.id)
        }
        return result
    }

    override fun hashCode(): Int {
        if (h == 0) {
            h = Objects.hashCode(id, type)
        }
        return h
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Principal

        if (type != other.type) return false
        if (id != other.id) return false

        return true
    }
}