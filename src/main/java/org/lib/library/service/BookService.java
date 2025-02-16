package org.lib.library.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.lib.library.entity.Book;
import org.lib.library.entity.Guest;
import org.lib.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
@NoArgsConstructor
@AllArgsConstructor
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    public Book findById(Long id) {
        return bookRepo.findById(id).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepo.findByIsAvailable(Boolean.TRUE);
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }

    public void issueBook(Long bookId, Guest guest) throws Exception {

        if(guest==null) {
            throw new Exception("Гость не может быть null");
        }

        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));

        if(!book.isAvailable()){
            throw new Exception("Эта книга уже выдана");
        }

        book.setGuest(guest);
        book.setAvailable(false);

        bookRepo.save(book);
    }

    public void returnBook(Long bookId) throws Exception {
        Book book = bookRepo.findById(bookId).orElseThrow(() -> new RuntimeException("Книга не найдена"));
        if(book.isAvailable()){
            throw new Exception("Эта книга уже сдана");
        }
        book.setGuest(null);

        bookRepo.save(book);
    }
}
