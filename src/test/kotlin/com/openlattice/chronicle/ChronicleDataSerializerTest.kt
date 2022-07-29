package com.openlattice.chronicle

import com.openlattice.chronicle.android.ChronicleData
import com.openlattice.chronicle.android.ChronicleUsageEvent
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import java.time.OffsetDateTime
import java.util.*

/**
 *
 * @author Matthew Tamayo-Rios &lt;matthew@getmethodic.com&gt;
 */
class ChronicleDataSerializerTest : AbstractJacksonSerializationTest<ChronicleData>() {
    override val sampleData: ChronicleData
        get() = ChronicleData((0 until 10).map {
                ChronicleUsageEvent(
                    UUID.randomUUID(),
                    RandomStringUtils.randomAlphanumeric(5),
                    RandomStringUtils.randomAlphanumeric(5),
                    RandomStringUtils.randomAlphanumeric(5),
                    RandomUtils.nextInt(0, 100),
                    OffsetDateTime.now(),
                    TimeZone.getDefault().id,
                    RandomStringUtils.randomAlphanumeric(5),
                    RandomStringUtils.randomAlphanumeric(5)
                )
            })


    override val clazz: Class<ChronicleData>
        get() = ChronicleData::class.java
    override fun logResult(result: SerializationResult<ChronicleData>) {
        logger.info(result.jsonString)
    }

}