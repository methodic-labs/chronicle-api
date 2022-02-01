package com.openlattice.chronicle.sensorkit

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 */
interface IOSSensorApi {
    companion object {
        const val SERVICE = "/chronicle"
        const val CONTROLLER = "/ios-sensor"
        const val BASE = SERVICE + CONTROLLER

        const val ORGANIZATION_ID = "organizationId"
        const val STUDY_ID = "studyId"
        const val DATASOURCE_ID = "datasourceId"
        const val PARTICIPANT_ID = "participantId"

        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
        const val STUDY_ID_PATH = "/{$STUDY_ID}"
        const val DATASOURCE_ID_PATH = "/{$DATASOURCE_ID}"
        const val PARTICIPANT_ID_PATH = "/{$PARTICIPANT_ID}"

    }

    /**
     * Uploads sensor data from iOS device
     *
     * @param organizationId - organizationId
     * @param studyId        - studyId
     * @param participantId  - participantId
     * @param deviceId       - unique Id obtained from https://developer.apple.com/documentation/uikit/uidevice/1620059-identifierforvendor
     * @param data           - A list of SensorDataSample objects.
     * @return number of rows written
     */
    @POST(BASE + ORGANIZATION_ID_PATH + STUDY_ID_PATH + PARTICIPANT_ID_PATH + DATASOURCE_ID_PATH )
    fun uploadIOSSensorData(
            @Path(ORGANIZATION_ID) organizationId: UUID,
            @Path(STUDY_ID) studyId: UUID,
            @Path(PARTICIPANT_ID) participantId: String,
            @Path(DATASOURCE_ID) deviceId: String,
            @Body data: List<SensorDataSample>
    ): Int

    // TODO: add download endpoint
}
