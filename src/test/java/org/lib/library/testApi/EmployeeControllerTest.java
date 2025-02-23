package org.lib.library.testApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lib.library.api.EmployeeController;
import org.lib.library.entity.Book;
import org.lib.library.service.BookService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebMvcTest(EmployeeController.class)
@Import(BookService.class)
public class EmployeeControllerTest {

    @Mock
    private BookService bookService;

    private MockMvc mockMvc;

    @Autowired
    private EmployeeController employeeController;

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setIsbn("12345");
        book.setName("Test Book");
        book.setAuthor("Test Author");
        book.setYearOfPublication("2021");
        book.setAvailable(true);
    }

    @Test
    public void testAddBook() throws Exception {
        doNothing().when(bookService).addBook(book);

        ResponseEntity<String> response = employeeController.addBook(book);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        String expectedMessage = book.toString() + "Книга успешно добавлена";
        assertEquals(expectedMessage, response.getBody());

        verify(bookService, times(1)).addBook(book);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees/new_book")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"isbn\":\"12345\"," +
                        "\"name\":\"Test Book\"," +
                        "\"author\":\"Test Author\"," +
                        "\"yearOfPublication\":\"2021\"," +
                        "\"isAvailable\":\"true\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Book [isbn=12345, name=Test Book, author=Test Author]Книга успешно добавлена"));

    }
}
