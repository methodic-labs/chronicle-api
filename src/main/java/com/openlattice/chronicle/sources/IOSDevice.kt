import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.openlattice.chronicle.sources.Datasource

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
class IOSDevice @JsonCreator constructor(
        @param:JsonProperty("model") private val model: String,
        @param:JsonProperty("version") private val version: String,
        @param:JsonProperty("deviceId") private val deviceId: String,
        @param:JsonProperty("name") private val name: String,
        @param:JsonProperty("systemName") private val systemName: String
) : Datasource