package com.openlattice.chronicle.sources

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.ChronicleFields
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
data class AndroidDevice(
    @JsonProperty(ChronicleFields.DEVICE) val device: String,
    @JsonProperty(ChronicleFields.MODEL) val model: String,
    @JsonProperty(ChronicleFields.CODENAME) val codename: String,
    @JsonProperty(ChronicleFields.BRAND) val brand: String,
    @JsonProperty(ChronicleFields.OS_VERSION) val osVersion: String,
    @JsonProperty(ChronicleFields.SDK_VERSION) val sdkVersion: String,
    @JsonProperty(ChronicleFields.PRODUCT) val product: String,
    @JsonProperty(ChronicleFields.DEVICE_ID) val deviceId: String,
    @JsonProperty(ChronicleFields.ADDITIONAL_INFO) val additionalInfo: Map<String, Any> = mapOf(),
    @JsonProperty(ChronicleFields.FCM_REGISTRATION_TOKEN) val fcmRegistrationToken: String = ""
) : SourceDevice
