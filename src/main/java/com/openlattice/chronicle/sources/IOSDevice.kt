package com.openlattice.chronicle.sources

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 * Describes an iOS device: https://developer.apple.com/documentation/uikit/uidevice
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
class IOSDevice @JsonCreator constructor(
        val name: String,
        val systemName: String,
        val model: String,
        val localizedModel: String,
        val version: String,
        val deviceId: String,
) : SourceDevice {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other !is IOSDevice) {
            return false
        }

        return name == other.name &&
                systemName == other.systemName &&
                model == other.model &&
                localizedModel == other.localizedModel &&
                version == other.version &&
                deviceId == other.deviceId

    }

    override fun hashCode(): Int {
        return Objects.hash(name, systemName, model, localizedModel, version, deviceId)
    }

    override fun toString(): String {
        return "IOSDevice{name=$name, systemName=$systemName, model=$model, localizedModel=$localizedModel, version=$version, deviceId=$deviceId }"
    }
}