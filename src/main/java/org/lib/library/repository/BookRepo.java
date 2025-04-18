package org.lib.library.repository;

import org.lib.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByIsAvailable(boolean b);
}
