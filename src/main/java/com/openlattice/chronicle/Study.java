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

public class Study extends AbstractSecurableObject {
    private       String              name;
    private final LinkedHashSet<UUID> participantIds;

    @JsonCreator
    public Study(
            @JsonProperty( SerializationConstants.ID_FIELD ) Optional<UUID> id,
            @JsonProperty( SerializationConstants.NAME_FIELD ) String name,
            @JsonProperty( SerializationConstants.TITLE_FIELD ) String title,
            @JsonProperty( SerializationConstants.DESCRIPTION_FIELD ) Optional<String> description,
            @JsonProperty( SerializationConstants.PARTICIPANT_IDS_FIELD ) LinkedHashSet<UUID> participantIds) {
        super( id, title, description );
        checkArgument( StringUtils.isNotBlank( name ), "Study name cannot be blank." );
        this.name = name;
        this.participantIds = participantIds;
    }

    public Study(
            UUID id,
            String name,
            String title,
            Optional<String> description,
            LinkedHashSet<UUID> configParticipantIds,
            String url ) {
        this( Optional.of( id ), name, title, description, configParticipantIds, url );
    }

    public Study(
            String name,
            String title,
            Optional<String> description,
            LinkedHashSet<UUID> configParticipantIds,
            String url ) {
        this( Optional.absent(), name, title, description, configParticipantIds, url );
    }

    @JsonProperty( SerializationConstants.NAME_FIELD )
    public String getName() {
        return name;
    }

    @Override public SecurableObjectType getCategory() {
        return SecurableObjectType.Study;
    }

    @JsonProperty( SerializationConstants.PARTICIPANT_IDS_FIELD )
    public LinkedHashSet<UUID> getParticipantIds() {
        return participantIds;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void addParticipantIds( Set<UUID> participantIds ) {
        this.participantIds.addAll( participantIds );
    }

    public void removeParticipantIds( Set<UUID> participantIds ) {
        this.participantIds.removeAll( participantIds );
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;

        Study study = (Study) o;

        if ( name != null ? !name.equals( study.name ) : study.name != null )
            return false;
        if ( participantIds != null ? !participantIds.equals( study.participantIds ) : study.participantIds != null )
            return false;
    }

    @Override public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( participantIds != null ? participantIds.hashCode() : 0 );
        return result;
    }

    @Override public String toString() {
        return "Study{" +
                "name='" + name + '\'' +
                ", participantIds=" + participantIds +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}