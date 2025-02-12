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

@Component
public class DataInitializer implements CommandLineRunner {

    Guest guest;
    BookRepo bookRepo;
    LibraryRepo libraryRepo;
    EmployeeRepo employeeRepo;
    PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Library library = new Library();
        library.setName("Архангельская научная библиотека им. Н.А. Добролюбова ");
        library.setAddress("г. Архангельск, ул. Партизанская, д. 2");
        libraryRepo.save(library);

        Book book = new Book();
        book.setIsbn("978-5-4461-4117-3");
        book.setAuthor("Джеймс Клир");
        book.setName("Атомные привычки");
        book.setYearOfPublication("2025");
        book.setAvailable(true);
        book.setLibraryId(library);
        bookRepo.save(book);

        Book book1 = new Book();
        book.setIsbn("978-5-04-101286-1");
        book.setAuthor("Джон Дакетт");
        book.setName("HTML и CSS. Разработка и создание веб-сайтов");
        book.setYearOfPublication("2022");
        book.setAvailable(true);
        book.setLibraryId(library);
        bookRepo.save(book1);


    }
}
