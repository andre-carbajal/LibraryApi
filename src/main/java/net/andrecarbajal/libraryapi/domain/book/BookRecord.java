package net.andrecarbajal.libraryapi.domain.book;

import lombok.Builder;
import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.category.Category;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Builder
public record BookRecord(
        String title,
        Author author,
        Publisher publisher,
        @DateTimeFormat LocalTime publicationTime,
        Category category,
        String description,
        Boolean available) {
}
