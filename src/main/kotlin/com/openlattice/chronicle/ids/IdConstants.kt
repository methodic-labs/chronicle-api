/*
 * Copyright (C) 2019. OpenLattice, Inc.
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
 *
 */

package com.openlattice.chronicle.ids

import java.util.UUID

enum class IdConstants(val id: UUID) {

    UNINITIALIZED(UUID(0L, 0L)),

    /* Organizations */

    SYSTEM_ORGANIZATION(UUID(1L, 0L)),


    /* ElasticSearch */
    LAST_WRITE_KEY_ID(UUID(1L, 10L)), // was UUID(0, 0)


    /* Postgres */

    // misc
    COUNT_ID(UUID(0L, 20L)),
    ID_ID(UUID(1L, 20L)),

    // metadata
    LAST_INDEX_ID(UUID(4L, 20L)),
    LAST_LINK_ID(UUID(5L, 20L)),
    LAST_WRITE_ID(UUID(6L, 20L)),

    // system
    SYSTEM_ID(UUID(10, 20)),
}