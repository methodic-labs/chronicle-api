package com.openlattice.chronicle.admin

import com.openlattice.chronicle.authorization.Principal
import retrofit2.http.GET
import retrofit2.http.Path

interface AdminApi {
    companion object {
        // @formatter:off
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/admin"
        const val BASE = SERVICE + CONTROLLER
        // @formatter:on

        const val RELOAD_CACHE = "/reload/cache"
        const val PRINCIPALS = "/principals"
        const val EVENT_STORAGE = "/event-storage"

        const val ID = "id"
        const val ID_PATH = "/{$ID}"
        const val NAME = "name"
        const val NAME_PATH = "/{$NAME}"
    }

    @GET(BASE + EVENT_STORAGE)
    fun moveToEventStorage()

    /**
     * Reload the all the in memory caches.
     */
    @GET(BASE + RELOAD_CACHE)
    fun reloadCache()

    @GET(BASE + RELOAD_CACHE + NAME_PATH)
    fun reloadCache(@Path(NAME) name: String)

    @GET(BASE + PRINCIPALS + ID_PATH)
    fun getUserPrincipals(@Path(ID) principalId: String): Set<Principal>

    /**
     * Retrieves all the principals for the current user.
     * @return All the principals of the current user.
     */
    @GET(BASE + PRINCIPALS)
    fun getCurrentUserPrincipals(): Set<Principal>
}
