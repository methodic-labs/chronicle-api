package com.openlattice.chronicle

import com.openlattice.chronicle.sensorkit.SensorSetting
import com.openlattice.chronicle.sensorkit.SensorType
import com.openlattice.chronicle.study.Study
import org.apache.commons.lang3.RandomStringUtils

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
class SensorSettingJacksonSerializerTest : AbstractJacksonSerializationTest<SensorSetting>() {
    override val sampleData: SensorSetting
        get() = SensorSetting(SensorType.values().toSet())
    override val clazz: Class<SensorSetting>?
        get() = SensorSetting::class.java
    override fun logResult(result: SerializationResult<SensorSetting>) {
        logger.info(result.jsonString)
    }

}