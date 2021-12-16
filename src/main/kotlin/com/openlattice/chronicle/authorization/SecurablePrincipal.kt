/*
 * Copyright (C) 2017. OpenLattice, Inc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * You can contact the owner of the copyright at support@openlattice.com
 *
 */
package com.openlattice.chronicle.authorization

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.google.common.base.Preconditions
import com.openlattice.chronicle.util.JsonFields.*
import java.util.*

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
open class SecurablePrincipal : AbstractSecurableObject {
    val aclKey: AclKey
    val principal: Principal

    @JsonCreator
    constructor(
            @JsonProperty(ID_FIELD) id: Optional<UUID>,
            @JsonProperty(PRINCIPAL) principal: Principal,
            @JsonProperty(TITLE_FIELD) title: String,
            @JsonProperty(DESCRIPTION_FIELD) description: Optional<String>
    ) : super(id, title, description) {
        this.principal = Preconditions.checkNotNull(principal)
        aclKey = AclKey(this.id)
    }

    constructor(
            aclKey: AclKey,
            principal: Principal,
            title: String,
            description: Optional<String>
    ) : super(aclKey[aclKey.size - 1], title, description) {
        this.principal = principal
        this.aclKey = aclKey
    }


    override val category: SecurableObjectType
        @JsonIgnore
        get() = SecurableObjectType.Principal


    val principalType: PrincipalType
        @JsonIgnore
        get() = principal.type


    val name: String
        @JsonIgnore
        get() = principal.id

    override fun toString(): String {
        return "SecurablePrincipal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", aclKey=" + aclKey +
                ", principal=" + principal +
                '}'
    }
}