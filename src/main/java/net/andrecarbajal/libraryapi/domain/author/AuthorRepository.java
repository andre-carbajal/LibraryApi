package net.andrecarbajal.libraryapi.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String AuthorName);
    Optional<Author> findByLastName(String AuthorLastName);
    Optional<Author> findByNameAndLastName(String name, String lastName);
}
