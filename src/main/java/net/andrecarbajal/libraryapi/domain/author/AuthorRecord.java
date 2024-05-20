package net.andrecarbajal.libraryapi.domain.author;

import jakarta.validation.constraints.NotBlank;

public record AuthorRecord(
        @NotBlank String name,
        @NotBlank String lastName
) {
}
