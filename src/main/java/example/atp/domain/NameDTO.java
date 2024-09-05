package example.atp.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record NameDTO(String name) {
}
