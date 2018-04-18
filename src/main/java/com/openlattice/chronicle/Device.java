package com.openlattice.chronicle;
// TODO: import com.openlattice.authorization.securable.AbstractSecurableObject;
// TODO: import com.openlattice.client.serialization.SerializationConstants;
// TODO: import com.openlattice.authorization.securable.SecurableObjectType;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;
import com.google.common.base.Optional;

import java.util.LinkedHashSet;
import java.util.UUID;
import java.util.Set;


import static com.google.common.base.Preconditions.checkArgument;

public class Device extends AbstractSecurableObject {
    private       String              name;

    @JsonCreator
    public Device(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description ) {
        super( id, description );
        checkArgument( StringUtils.isNotBlank( name ), "Device name cannot be blank." );
        this.name = name;
    }

    public Device(
            UUID id,
            String name,
            Optional<String> description ) {
        this( Optional.of( id ), name, description );
    }

    public Device(
            String name,
            Optional<String> description ) {
        this( Optional.absent(), name, description );
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public String getName() {
        return name;
    }

    @Override public SecurableObjectType getCategory() {
        return SecurableObjectType.Device;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;

        Device device = (Device) o;

        if ( name != null ? !name.equals( device.name ) : device.name != null )
            return false;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        return result;
    }

    @Override public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}