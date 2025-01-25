package org.lib.library.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lib.library.entity.Book;
import org.lib.library.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/catalog")
@AllArgsConstructor
@NoArgsConstructor
public class GuestController {
    BookService bookService;

    @GetMapping("/book")
    public List<Book> getAllBook() {
        return bookService.findAll();
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }
}
