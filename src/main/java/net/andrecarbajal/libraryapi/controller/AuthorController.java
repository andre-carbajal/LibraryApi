package net.andrecarbajal.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.author.AuthorRecord;
import net.andrecarbajal.libraryapi.domain.author.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author Controller")
@RestController
@RequestMapping("/api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new author")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorRecord data) {
        Author author = Author.builder()
                .name(data.name())
                .lastName(data.lastName())
                .build();
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    @Operation(summary = "Get all authors")
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an author by ID")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return authorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update an author by ID")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody AuthorRecord data) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        author.setName(data.name());
        author.setLastName(data.lastName());
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete an author by ID")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }
}
