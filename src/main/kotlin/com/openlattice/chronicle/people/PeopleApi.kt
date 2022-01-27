package com.openlattice.chronicle.people

import retrofit2.http.POST
import java.util.UUID

interface PeopleApi {

    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/people"
        const val BASE = SERVICE + CONTROLLER
    }

    @POST(BASE)
    fun createPerson(person: Person): UUID
}
