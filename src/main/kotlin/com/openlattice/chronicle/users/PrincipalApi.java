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

package com.openlattice.chronicle.users;

import com.auth0.json.mgmt.users.User;
import com.openlattice.chronicle.authorization.AclKey;
import com.openlattice.chronicle.authorization.Principal;
import com.openlattice.chronicle.authorization.Role;
import com.openlattice.chronicle.authorization.SecurablePrincipal;
import com.openlattice.chronicle.base.OK;
import java.util.Map;
import java.util.Set;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PrincipalApi {
    /*
     * These determine the service routing for the LB
     */
    String SERVICE    = "/datastore";
    String CONTROLLER = "/principals";
    String BASE       = SERVICE + CONTROLLER;

    /*
     * Path variables
     */
    String USER_ID      = "userId";
    String SEARCH_QUERY = "searchQuery";

    /*
     * Fixed paths
     */
    String CURRENT      = "/current";
    String CREDENTIAL   = "/credential";
    String DB           = "/db";
    String EMAIL        = "/email";
    String ROLES        = "/roles";
    String SEARCH       = "/search";
    String SYNC         = "/sync";
    String UPDATE       = "/update";
    String USERS        = "/users";
    String SEARCH_EMAIL = SEARCH + EMAIL;

    /*
     * Variable paths
     */
    String USER_ID_PATH            = "/{" + USER_ID + "}";
    String SEARCH_QUERY_PATH       = "/{" + SEARCH_QUERY + "}";
    String EMAIL_SEARCH_QUERY_PATH = "/{" + SEARCH_QUERY + ":.+" + "}";

    @POST( BASE )
    SecurablePrincipal getSecurablePrincipal( @Body Principal principal );

    @GET( BASE + USERS )
    Map<String, User> getAllUsers();

    @GET( BASE + ROLES + CURRENT )
    Set<SecurablePrincipal> getCurrentRoles();

    @GET( BASE + ROLES )
    Map<AclKey, Role> getAvailableRoles();

    @GET( BASE + USERS + USER_ID_PATH )
    User getUser( @Path( USER_ID ) String userId );

    @POST( BASE + USERS )
    Map<String, User> getUsers( @Body Set<String> userIds );

    @POST( BASE + USERS + SEARCH )
    Map<String, User> searchUsers( @Body Auth0UserSearchFields fields );

    /**
     * Activates a user in the OpenLattice system. This call must be made once before a user will be available for use
     * in authorization policies.
     *
     * @return Nothing
     */
    @GET( BASE + SYNC )
    OK syncCallingUser();

    @POST( BASE + UPDATE )
    OK addPrincipalToPrincipal( @Body DirectedAclKeys directedAclKeys );

    @DELETE( BASE + UPDATE )
    OK removePrincipalFromPrincipal( @Body DirectedAclKeys directedAclKeys );

    @DELETE( BASE + USERS + USER_ID_PATH )
    OK deleteUserAccount( @Path( USER_ID ) String userId );
}
