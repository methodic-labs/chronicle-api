package com.openlattice.chronicle.users

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.openlattice.chronicle.authorization.AclKey
import com.openlattice.chronicle.data.SerializationConstants
import com.openlattice.chronicle.util.JsonFields.SRC
import com.openlattice.chronicle.util.JsonFields.TARGET
import java.util.*

class DirectedAclKeys @JsonCreator constructor(
    @JsonProperty(TARGET) val target: AclKey,
    @JsonProperty(SRC) val source: AclKey
)