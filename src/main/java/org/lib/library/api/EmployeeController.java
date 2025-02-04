package org.lib.library.api;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.lib.library.entity.Book;
import org.lib.library.entity.Guest;
import org.lib.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeController {
    private BookService bookService;

    @PostMapping("/new_book")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @DeleteMapping("/book/delete/{bookId}")
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
        return "Книга удалена";
    }

    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/book/{bookId}/issue")
    public ResponseEntity<String> issueBook(@PathVariable Long bookId, @RequestBody Guest guest) {
        try{
            bookService.issueBook(bookId, guest);
            return new ResponseEntity<>("Книга успешно выдана", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/book/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        try{
            bookService.returnBook(bookId);
            return new ResponseEntity<>("Книга успешно возвращена" , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }









}
