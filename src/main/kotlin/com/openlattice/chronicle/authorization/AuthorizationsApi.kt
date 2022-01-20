package com.openlattice.chronicle.authorization

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface AuthorizationsApi {
    companion object {
        /*
        * These determine the service routing for the LB
        */
        const val SERVICE = "/datastore"
        const val CONTROLLER = "/authorizations"
        const val BASE = SERVICE + CONTROLLER

        const val OBJECT_TYPE = "objectType"
        const val PERMISSION = "permission"
        const val PAGING_TOKEN = "pagingToken"
    }

    @POST(BASE)
    fun checkAuthorizations(@Body queries: Set<AccessCheck>): Iterable<Authorization>

    /**
     * Returns paged results for all authorized objects of specified objectType, that the current user has specified permission for.
     * @param objectType Required field. Specifying the Securable Object Type that user wants to search for.
     * @param permission Required field. Specifying the permission the user must have for the accessible objects.
     * @param pagingToken Unrequired field. One may use the paging token from previous search result to get to the next page of results.
     * @return
     */
    @GET(BASE)
    fun getAccessibleObjects(
            @Query(
                    OBJECT_TYPE
            ) objectType: SecurableObjectType,
            @Query(PERMISSION) permission: Permission,
            @Query(PAGING_TOKEN) pagingToken: String
    ): AuthorizedObjectsSearchResult
}
