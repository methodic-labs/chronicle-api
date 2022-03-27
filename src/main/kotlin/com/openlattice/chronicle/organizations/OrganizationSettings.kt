package com.openlattice.chronicle.organizations

import com.openlattice.chronicle.settings.AppComponent
import com.openlattice.chronicle.settings.AppUsageFrequency
import com.openlattice.chronicle.study.StudySetting

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class ChronicleDataCollectionSettings(
        val appUsageFrequency: AppUsageFrequency = AppUsageFrequency.DAILY
) : StudySetting

data class OrganizationSettings(
        val chronicleDataCollection: ChronicleDataCollectionSettings = ChronicleDataCollectionSettings(),
        val appSettings: Map<AppComponent,Map<String,Any>> = mutableMapOf()
)