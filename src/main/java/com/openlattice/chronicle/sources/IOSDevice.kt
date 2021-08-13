package com.openlattice.chronicle.sources

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.IOSDeviceFields
import java.util.*

/**
 * @author alfoncenzioka &lt;alfonce@openlattice.com&gt;
 *
 * Describes an iOS device: https://developer.apple.com/documentation/uikit/uidevice
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
class IOSDevice @JsonCreator constructor(
        @param:JsonProperty(IOSDeviceFields.NAME) val name: String,
        @param:JsonProperty(IOSDeviceFields.SYSTEM_NAME) val systemName: String,
        @param:JsonProperty(IOSDeviceFields.MODEL) val model: String,
        @param:JsonProperty(IOSDeviceFields.LOCALIZED_MODEL) val localizedModel: String,
        @param:JsonProperty(IOSDeviceFields.VERSION) val version: String,
        @param:JsonProperty(IOSDeviceFields.DEVICE_ID) val deviceId: String,
) : Datasource {

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