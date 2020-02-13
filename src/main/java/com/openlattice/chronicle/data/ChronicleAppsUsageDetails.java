package com.openlattice.chronicle.data;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.util.Map;
import java.util.Set;

/**
 * describes chronicle_user_apps entity and chronicle_used_by associations
 */

public class ChronicleAppsUsageDetails {
    private final Map<FullQualifiedName, Set<Object>> entityDetails;
    private final Map<FullQualifiedName, Set<Object>> associationDetails;

    public ChronicleAppsUsageDetails(
            Map<FullQualifiedName, Set<Object>> entityDetails,
            Map<FullQualifiedName, Set<Object>> associationDetails
    ) {
        this.entityDetails = entityDetails;
        this.associationDetails = associationDetails;
    }

    public Map<FullQualifiedName, Set<Object>> getEntityDetails() {
        return entityDetails;
    }

    public Map<FullQualifiedName, Set<Object>> getAssociationDetails() {
        return associationDetails;
    }
}
