package net.andrecarbajal.libraryapi.domain.publisher;

import jakarta.validation.constraints.NotBlank;

public record PublisherRecord(
        @NotBlank String name
) {
}
