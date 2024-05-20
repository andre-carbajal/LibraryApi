package net.andrecarbajal.libraryapi.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserRecord(
        @NotBlank String username,
        @NotBlank String password
) {
}
