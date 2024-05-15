package net.andrecarbajal.libraryapi.domain.book;

import jakarta.validation.constraints.NotBlank;
import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.category.Category;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

public record BookRecord(
        @NotBlank String title,
        @NotBlank Author author,
        @NotBlank Publisher publisher,
        @DateTimeFormat LocalTime publicationTime,
        @NotBlank Category category,
        @NotBlank String description,
        @NotBlank Boolean available) {
}
