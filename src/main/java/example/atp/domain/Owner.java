package example.atp.domain;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.GeneratedValue.Type;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@MappedEntity
public record Owner
        (
                // The ID of the class uses a generated sequence value
                @Id @GeneratedValue(Type.IDENTITY) @Nullable Long id,
                // Each component of the record maps to a database column
                String name,
                int age
        ) {
    // A secondary constructor makes it easier to instantiate
    // new instances without an ID
    public Owner(String name, int age) {
        this(null, name, age);
    }
}
