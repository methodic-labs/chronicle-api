package com.openlattice.chronicle.users

import retrofit2.http.GET

interface UserApi {

    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/v3/user"
        const val BASE = SERVICE + CONTROLLER

        const val SYNC_PATH = "/sync"
    }

    @GET(BASE + SYNC_PATH)
    fun sync()
}
