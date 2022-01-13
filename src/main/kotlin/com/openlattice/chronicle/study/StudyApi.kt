package com.openlattice.chronicle.study

import com.google.common.base.Optional
import com.openlattice.chronicle.ChronicleStudyApi
import com.openlattice.chronicle.sources.Datasource
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface StudyApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/study" //TODO: Merge with existing ChronicleStudyApi
        const val BASE = SERVICE + CONTROLLER
    }

    /**
     * Enrolls a participant's data datasource in a study. Currently the only supported datasource is an Android device, though
     * though that may change in the future.
     * <P>
     * Due to privacy changes in Android the device id is not a reliable way of tracking devices.
     * we are leaving the study path in for now, because we don't know that participant's across studies are unique
     *
     *
     * @param studyId       The id of the study with which to enroll the partipant's datasource.
     * @param participantId The participant id which the device will be associated with.
     * @param datasourceId  A datasource specific id.
     * @param datasource    Datasource specific information.
     * @return The internal chronicle id for a device. It can be used to track a single device across resets, app uninstalls,
     * etc.
    </P> */
    @POST(BASE)
    fun createStudy (@Body study: Study): UUID

}