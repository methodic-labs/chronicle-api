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

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.common.base.Preconditions
import com.openlattice.chronicle.util.JsonFields
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * Base class for all securable objects in the system.
 */
@JsonInclude(
        value = JsonInclude.Include.NON_ABSENT
) //This means empty collections will not be included in generated JSON.
abstract class AbstractSecurableObject private constructor(
        @JsonProperty(JsonFields.ID_FIELD) var id: UUID,
        @JsonProperty(JsonFields.TITLE_FIELD) var title: String,
        @JsonProperty(JsonFields.DESCRIPTION_FIELD) var description: String,
        @JsonIgnore private val idPresent: Boolean
) {
    init {

        /*
         * There is no logical requirement that the title not be blank, it would just be very confusing to have a bunch
         * of organizations with no title whatsoever. This can be relaxed in the future.
         */
        Preconditions.checkArgument(StringUtils.isNotBlank(title), "Title cannot be blank.")
    }

    /**
     * @param id The UUID of the securable object. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     */
    protected constructor(
            id: UUID,
            title: String,
            description: Optional<String>
    ) : this(id, title, description, true)

    /**
     * @param id An optional id for the securable object in the form a UUID.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     */
    protected constructor(
            id: Optional<UUID>,
            title: String,
            description: Optional<String>
    ) : this(id.orElseGet { UUID.randomUUID() }, title, description, id.isPresent)

    /**
     * @param id The id of the securable object in the form of a UUID. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     * @param idPresent Whether the id was present at creation time or whether it was generate randomly.
     */
    private constructor(
            id: UUID,
            title: String,
            description: Optional<String>,
            idPresent: Boolean
    ) : this(id, title, description.orElse(""), idPresent)

    /**
     * Serialization constructor
     *
     * @param id The id of the securable object in the form of a UUID. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     */
    protected constructor(
            id: UUID,
            title: String,
            description: String
    ) : this(id, title, description, true)

    override fun toString(): String {
        return ("AbstractSecurableObject [id=" + id + ", title=" + title + ", description=" + description
                + ", idPresent=" + idPresent + "]")
    }

    @JsonIgnore
    fun wasIdPresent(): Boolean {
        return idPresent
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractSecurableObject

        if (id != other.id) return false
        if (idPresent != other.idPresent) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + idPresent.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    abstract val category: SecurableObjectType

    /**
     * @param id The id of the securable object in the form of a UUID. Must not be null.
     * @param title The title of the securable object. Must not be blank.
     * @param description An optional description for the object. Can be blank or null.
     * @param idPresent Whether the id was present at creation time or whether it was generate randomly.
     */

}