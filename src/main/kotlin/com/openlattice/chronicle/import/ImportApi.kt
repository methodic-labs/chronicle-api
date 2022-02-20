package com.openlattice.chronicle.import

import retrofit2.http.POST

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface ImportApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/import"
        const val BASE = SERVICE + CONTROLLER

        const val STUDIES = "/studies"
    }

    @POST(BASE + STUDIES)
    fun importStudies(config: ImportStudiesConfiguration)
}