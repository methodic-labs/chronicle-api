package com.openlattice.chronicle.sources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Matthew Tamayo-Rios &lt;matthew@openlattice.com&gt;
 */
@JsonTypeInfo( use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "className" )
@JsonSubTypes( {
        @JsonSubTypes.Type( value = AndroidDevice.class, name = "AndroidDevice" )
} )
public interface Datasource {
}

