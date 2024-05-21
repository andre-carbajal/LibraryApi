package net.andrecarbajal.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.author.AuthorRepository;
import net.andrecarbajal.libraryapi.domain.book.Book;
import net.andrecarbajal.libraryapi.domain.book.BookRecord;
import net.andrecarbajal.libraryapi.domain.book.BookRepository;
import net.andrecarbajal.libraryapi.domain.category.Category;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;
import net.andrecarbajal.libraryapi.domain.publisher.PublisherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Book Controller")
@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new book")
    public ResponseEntity<Book> createBook(@RequestBody BookRecord data) {
        Author author = authorRepository.findById(data.authorId()).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        Publisher publisher = publisherRepository.findById(data.publisherId()).orElse(null);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        Category category = Category.valueOf(data.category().toUpperCase());

        Book book = Book.builder().title(data.title()).author(author).publisher(publisher).publication_time(data.publicationTime()).category(category).description(data.description()).available(data.available()).build();

        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update a book by ID")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRecord data) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        Author author = authorRepository.findById(data.authorId()).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        Publisher publisher = publisherRepository.findById(data.publisherId()).orElse(null);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        Category category = Category.valueOf(data.category().toUpperCase());

        book.setTitle(data.title());
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublication_time(data.publicationTime());
        book.setCategory(category);
        book.setDescription(data.description());
        book.setAvailable(data.available());

        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a book by ID")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{name}/{lastName}")
    @Operation(summary = "Get books by author's name and last name")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable(required = false) String name, @PathVariable(required = false) String lastName) {
        List<Book> books;
        if (name != null && !name.equals("null")) {
            Author author = authorRepository.findByName(name).orElse(null);
            books = bookRepository.findByAuthor(author);
        } else if (lastName != null && !lastName.equals("null")) {
            Author author = authorRepository.findByLastName(lastName).orElse(null);
            books = bookRepository.findByAuthor(author);
        } else {
            Author author = authorRepository.findByNameAndLastName(name, lastName).orElse(null);
            books = bookRepository.findByAuthor(author);
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/publisher/{name}")
    @Operation(summary = "Get books by publisher")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable String name) {
        Publisher publisher = publisherRepository.findByName(name).orElse(null);
        return ResponseEntity.ok(bookRepository.findByPublisher(publisher));
    }

    @GetMapping("/title/{title}")
    @Operation(summary = "Get books by title")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get books by category")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(bookRepository.findByCategory(category));
    }

    @GetMapping("/available/{available}")
    @Operation(summary = "Get books by availability")
    public ResponseEntity<List<Book>> getBooksByAvailability(@PathVariable Boolean available) {
        return ResponseEntity.ok(bookRepository.findByAvailable(available));
    }

    @GetMapping("/filter/{authorName}/{publisherName}/{title}/{category}/{available}")
    @Operation(summary = "Get books by filtering criteria (null for no filter)")
    public ResponseEntity<List<Book>> getBooksByFilter(
            @PathVariable(required = false) String authorName,
            @PathVariable(required = false) String publisherName,
            @PathVariable(required = false) String title,
            @PathVariable(required = false) String category,
            @PathVariable(required = false) String available) {

        List<Book> books = bookRepository.findAll();

        if (authorName != null && !authorName.equals("null")) {
            Author author = authorRepository.findByName(authorName).orElse(null);
            books = books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
        }

        if (publisherName != null && !publisherName.equals("null")) {
            Publisher publisher = publisherRepository.findByName(publisherName).orElse(null);
            books = books.stream().filter(book -> book.getPublisher().equals(publisher)).collect(Collectors.toList());
        }

        if (title != null && !title.equals("null")) {
            books = books.stream().filter(book -> book.getTitle().equals(title)).collect(Collectors.toList());
        }

        if (category != null && !category.equals("null")) {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            books = books.stream().filter(book -> book.getCategory().equals(categoryEnum)).collect(Collectors.toList());
        }

        if (available != null && !available.equals("null")) {
            Boolean availableBoolean = Boolean.valueOf(available);
            books = books.stream().filter(book -> book.getAvailable().equals(availableBoolean)).collect(Collectors.toList());
        }

        return ResponseEntity.ok(books);
    }
}
