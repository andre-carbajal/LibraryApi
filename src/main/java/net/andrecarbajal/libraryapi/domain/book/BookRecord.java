package net.andrecarbajal.libraryapi.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BookRecord(
        @NotBlank String title,
        @NotBlank Long authorId,
        @NotBlank Long publisherId,
        @NotNull @DateTimeFormat LocalDate publicationTime,
        @NotBlank String category,
        @NotBlank String description,
        @NotBlank Boolean available) {
}
