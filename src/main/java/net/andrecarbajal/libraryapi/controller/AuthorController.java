package net.andrecarbajal.libraryapi.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.author.AuthorRecord;
import net.andrecarbajal.libraryapi.domain.author.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    AuthorRepository authorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Author> createAuthor(AuthorRecord authorRecord) {
        Author author = Author.builder()
                .name(authorRecord.name())
                .build();
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthors() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, AuthorRecord authorRecord) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        author.setName(authorRecord.name());
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }

}
