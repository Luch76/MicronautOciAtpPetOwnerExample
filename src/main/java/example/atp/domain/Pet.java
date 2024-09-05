package example.atp.domain;

import java.util.UUID;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public record Pet(
        // Use an autopopulated UUID for the primary key
        @Id @AutoPopulated @Nullable UUID id,
        String name,
        // A relation is defined between Pet and Owner
        @Relation(Relation.Kind.MANY_TO_ONE)
        Owner owner,
        // Optional columns can be defined by specifying Nullable
        @Nullable
        PetType type) {

    // Default values can be set with the Record initializer
    public Pet {
        if (type == null) {
            type = PetType.DOG;
        }
    }

    // Secondary record constructors make it easier to construct instances
    public Pet(String name, Owner owner) {
        this(null, name, owner, null);
    }

    public Pet(String name, Owner owner, PetType type) {
        this(null, name, owner, type);
    }

    public enum PetType {
        DOG,
        CAT
    }
}
