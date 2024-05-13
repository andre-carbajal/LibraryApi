package net.andrecarbajal.libraryapi.domain.book;

import net.andrecarbajal.libraryapi.domain.author.Author;
import net.andrecarbajal.libraryapi.domain.category.Category;
import net.andrecarbajal.libraryapi.domain.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    List<Book> findByPublisher(Publisher publisher);
    List<Book> findByTitle(String title);
    List<Book> findByCategory(Category category);
    List<Book> findByAvailable(Boolean available);
}
