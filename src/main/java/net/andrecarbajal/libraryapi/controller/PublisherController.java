package net.andrecarbajal.libraryapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;
import net.andrecarbajal.libraryapi.domain.publisher.PublisherRecord;
import net.andrecarbajal.libraryapi.domain.publisher.PublisherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Publisher Controller")
@RestController
@RequestMapping("/api/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;

    @PostMapping
    @Transactional
    @Operation(summary = "Create a new publisher")
    public ResponseEntity<Publisher> createPublisher(@RequestBody PublisherRecord data) {
        Publisher publisher = Publisher.builder().name(data.name()).build();
        publisherRepository.save(publisher);
        return ResponseEntity.ok(publisher);
    }

    @GetMapping
    @Operation(summary = "Get all publishers")
    public ResponseEntity<List<Publisher>> getPublisher() {
        return ResponseEntity.ok(publisherRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a publisher by ID")
    public ResponseEntity<Publisher> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(publisherRepository.findById(id).orElse(null));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update a publisher by ID")
    public ResponseEntity<Publisher> updateAuthor(@PathVariable Long id, PublisherRecord data) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        publisher.setName(data.name());
        publisherRepository.save(publisher);
        return ResponseEntity.ok(publisher);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Delete a publisher by ID")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        if (publisher == null) {
            return ResponseEntity.notFound().build();
        }
        publisherRepository.delete(publisher);
        return ResponseEntity.noContent().build();
    }
}
