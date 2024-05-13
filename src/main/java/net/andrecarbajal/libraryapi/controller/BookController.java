package net.andrecarbajal.libraryapi.controller;

import net.andrecarbajal.libraryapi.domain.book.Book;
import net.andrecarbajal.libraryapi.domain.book.BookRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    public ResponseEntity<Book> createBook(BookRecord book) {
        return null;
    }
}
