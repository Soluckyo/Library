package org.lib.library.config;

import org.lib.library.entity.Book;
import org.lib.library.entity.Employee;
import org.lib.library.entity.Guest;
import org.lib.library.entity.Library;
import org.lib.library.entity.Role;
import org.lib.library.repository.BookRepo;
import org.lib.library.repository.EmployeeRepo;
import org.lib.library.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {
//
//    Guest guest;
//    BookRepo bookRepo;
//    LibraryRepo libraryRepo;
//    EmployeeRepo employeeRepo;
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public DataInitializer(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder, LibraryRepo libraryRepo, BookRepo bookRepo) {
//        this.employeeRepo = employeeRepo;
//        this.passwordEncoder = passwordEncoder;
//        this.libraryRepo = libraryRepo;
//        this.bookRepo = bookRepo;
//
//    }
//
    @Override
    public void run(String... args) throws Exception {

//        Library library1 = libraryRepo.findById(3L).get();
//
//        Book book1 = new Book();
//        book1.setIsbn("978-5-699-54574-2");
//        book1.setAuthor("Кэти Сьера и Берт Бейтс");
//        book1.setName("Изучаем Java. 2-е издание");
//        book1.setYearOfPublication("2022");
//        book1.setAvailable(true);
//        book1.setLibraryId(library1);
//        bookRepo.save(book1);


    }
}
