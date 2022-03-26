package com.openlattice.chronicle.notifications

import com.openlattice.chronicle.authorization.Principal

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
class ResearcherNotificationSetting(
        principal: Principal,
        notificationType: NotificationType,
        deliveryTypes : Set<DeliveryType>

)

enum class DeliveryType {
    SMS,
    EMAIL
}

enum class NotificationType {
    AUDIT_EVENT,
    PASSIVE_DATA_COLLECTION_COMPLIANCE,
    TUD_SUBMISSION_COMPLIANCE,
    OPERATIONAL_CHECKS,
    ENROLLMENT
}