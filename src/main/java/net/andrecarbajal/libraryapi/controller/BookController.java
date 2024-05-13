package net.andrecarbajal.libraryapi.controller;

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

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    BookRepository bookRepository;
    AuthorRepository authorRepository;
    PublisherRepository publisherRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Book> createBook(BookRecord bookRecord) {
        Book book = Book.builder()
                .title(bookRecord.title())
                .author(bookRecord.author())
                .publisher(bookRecord.publisher())
                .publicationTime(bookRecord.publicationTime())
                .category(bookRecord.category())
                .description(bookRecord.description())
                .available(bookRecord.available())
                .build();

        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Book> updateBook(@PathVariable Long id, BookRecord bookRecord) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        book.setTitle(bookRecord.title());
        book.setAuthor(bookRecord.author());
        book.setPublisher(bookRecord.publisher());
        book.setPublicationTime(bookRecord.publicationTime());
        book.setCategory(bookRecord.category());
        book.setDescription(bookRecord.description());
        book.setAvailable(bookRecord.available());

        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        bookRepository.delete(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookRepository.findById(id).orElse(null));
    }

    @GetMapping("/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String authorName) {
        Author author = authorRepository.findByName(authorName).orElse(null);
        return ResponseEntity.ok(bookRepository.findByAuthor(author));
    }

    @GetMapping("/{publisherName}")
    public ResponseEntity<List<Book>> getBooksByPublisher(@PathVariable String publisherName) {
        Publisher publisher = publisherRepository.findByName(publisherName).orElse(null);
        return ResponseEntity.ok(bookRepository.findByPublisher(publisher));
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(bookRepository.findByTitle(title));
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(bookRepository.findByCategory(category));
    }

    @GetMapping("/{available}")
    public ResponseEntity<List<Book>> getBooksByAvailability(@PathVariable Boolean available) {
        return ResponseEntity.ok(bookRepository.findByAvailable(available));
    }
}
