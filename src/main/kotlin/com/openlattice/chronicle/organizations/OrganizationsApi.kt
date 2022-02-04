package com.openlattice.chronicle.organizations

import com.openlattice.chronicle.settings.AppComponent
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface OrganizationsApi {
    companion object {
        // @formatter:off
        const val SERVICE = "/chronicle/v3"
        const val CONTROLLER = "/organizations"
        const val BASE = SERVICE + CONTROLLER
        // @formatter:on

        const val ORGANIZATION_ID = "organizationId"
        const val ORGANIZATION_ID_PATH = "/{$ORGANIZATION_ID}"
    }

    @POST(BASE)
    fun createOrganization(organization: Organization): UUID

    @GET(BASE + ORGANIZATION_ID_PATH)
    fun getOrganization(@Path(ORGANIZATION_ID) organizationId: UUID): Organization

    fun searchOrganizations(): Collection<Organization>
    fun getOrganizations(): Collection<Organization>

    fun getOrganizationSettings(): OrganizationSettings
    fun getChronicleDataCollectionSettings(organizationId: UUID): ChronicleDataCollectionSettings
    fun getAppComponentSettings(organizationId: UUID, appComponent: AppComponent): Map<String, Any>

    fun setOrganizationSettings(organizationId: UUID, orgSettings: OrganizationSettings)
    fun setChronicleDataCollectionSettings(
        organizationId: UUID,
        dataCollectionSettings: ChronicleDataCollectionSettings
    )

    fun setAppComponentSettings(organizationId: UUID, appComponent: AppComponent, settings: Map<String, Any>)
}
