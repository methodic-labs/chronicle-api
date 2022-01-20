package com.openlattice.chronicle.organizations

import com.openlattice.chronicle.ids.IdConstants
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
data class Organization(
    var id: UUID = IdConstants.UNINITIALIZED.id,
    var title: String,
    var description: String = "",
    var settings: Map<String, Any> = mutableMapOf()

)