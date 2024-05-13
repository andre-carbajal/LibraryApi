package net.andrecarbajal.libraryapi.domain.book;

import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;

import java.time.LocalTime;

public record BookRecord(
        String title,
        Author author,
        Publisher publisher,
        LocalTime publicationTime,
        Category category,
        String description,
        Boolean available
) {
}
