package org.lib.library.testApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lib.library.api.EmployeeController;
import org.lib.library.entity.Book;
import org.lib.library.service.BookService;
import org.lib.library.service.EmployeeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private EmployeeController employeeController;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setIsbn("123");
        book.setName("John Doe");
        book.setAuthor("John Doe");
        book.setAvailable(true);
    }

    @Test
    public void testAddBook(){
        doNothing().when(bookService).addBook(book);

        ResponseEntity<String> response = employeeController.addBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String expectedMessage = book.toString() + "Книга успешно добавлена";
        assertEquals(expectedMessage, response.getBody());

        verify(bookService, times(1)).addBook(book);
    }
}
