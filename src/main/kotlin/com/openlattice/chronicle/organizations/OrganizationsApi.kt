package com.openlattice.chronicle.organizations

import com.openlattice.chronicle.settings.AppComponent
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
interface OrganizationsApi {
    companion object {
        // @formatter:off
        const val  SERVICE = "/chronicle"
        const val  CONTROLLER = "/organizations"
        const val  BASE = SERVICE + CONTROLLER
        // @formatter:on
    }

    fun createOrganization(organizationPrincipal: OrganizationPrincipal): UUID
    fun searchOrganizations(): Collection<OrganizationPrincipal>
    fun getOrganizations(): Collection<OrganizationPrincipal>

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