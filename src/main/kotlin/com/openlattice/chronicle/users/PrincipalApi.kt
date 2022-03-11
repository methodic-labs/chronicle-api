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

import com.auth0.json.mgmt.users.User
import com.openlattice.chronicle.authorization.AclKey
import com.openlattice.chronicle.authorization.Principal
import com.openlattice.chronicle.authorization.Role
import com.openlattice.chronicle.authorization.SecurablePrincipal
import com.openlattice.chronicle.base.OK
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PrincipalApi {

    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/principal"
        const val BASE = SERVICE + CONTROLLER

        const val CURRENT = "/current"
        const val ROLES = "/roles"
        const val SEARCH = "/search"
        const val SYNC = "/sync"
        const val UPDATE = "/update"
        const val USERS = "/users"
        const val USER_ID = "userId"
        const val USER_ID_PATH = "/{$USER_ID}"
    }

    @POST(BASE)
    fun getSecurablePrincipal(@Body principal: Principal): SecurablePrincipal

    @GET(BASE + USERS)
    fun getAllUsers(): Map<String, User>

    @GET(BASE + ROLES + CURRENT)
    fun getCurrentRoles(): Set<SecurablePrincipal>

    @GET(BASE + ROLES)
    fun getAvailableRoles(): Map<AclKey, Role>

    @GET(BASE + USERS + USER_ID_PATH)
    fun getUser(@Path(USER_ID) userId: String): User

    @POST(BASE + USERS)
    fun getUsers(@Body userIds: Set<String>): Map<String, User>

    @POST(BASE + USERS + SEARCH)
    fun searchUsers(@Body fields: Auth0UserSearchFields): Map<String, User>

    /**
     * Activates a user in the OpenLattice system. This call must be made once before a user will be available for use
     * in authorization policies.
     *
     * @return Nothing
     */
    @GET(BASE + SYNC)
    fun syncCallingUser(): OK

    @POST(BASE + UPDATE)
    fun addPrincipalToPrincipal(@Body directedAclKeys: DirectedAclKeys): OK

    @DELETE(BASE + UPDATE)
    fun removePrincipalFromPrincipal(@Body directedAclKeys: DirectedAclKeys): OK

    @DELETE(BASE + USERS + USER_ID_PATH)
    fun deleteUserAccount(@Path(USER_ID) userId: String): OK
}
