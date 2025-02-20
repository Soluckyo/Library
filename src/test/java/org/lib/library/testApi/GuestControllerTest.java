package org.lib.library.testApi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lib.library.api.GuestController;
import org.lib.library.entity.Book;
import org.lib.library.entity.Guest;
import org.lib.library.entity.Library;
import org.lib.library.service.BookService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuestControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private Library library;

    @Mock
    private Guest guest;

    @InjectMocks
    private GuestController guestController;

    private List<Book> mockBooks;
    Book book3;

    @BeforeEach
    public void setUp() {

        Book book1 = new Book();
        book1.setIsbn("12345");
        book1.setName("Book 1");
        book1.setAuthor("Author 1");
        book1.setYearOfPublication("2002");
        book1.setLibraryId(library);
        book1.setGuest(null);
        book1.setAvailable(true);

        Book book2 = new Book();
        book2.setIsbn("67890");
        book2.setName("Book 2");
        book2.setAuthor("Author 2");
        book2.setYearOfPublication("2020");
        book2.setAvailable(false);
        book2.setLibraryId(library);
        book2.setGuest(guest);

        mockBooks = Arrays.asList(book1, book2);

        book3 = new Book();
        book3.setIsbn("67890");
        book3.setName("Book 3");
        book3.setAuthor("Author 3");
        book3.setYearOfPublication("2022");
        book3.setAvailable(false);
        book3.setLibraryId(library);
        book3.setGuest(guest);
    }

    @Test
    void testGetAllBooks(){
        when(bookService.findAll()).thenReturn(mockBooks);

        List<Book> result = guestController.getAllBook();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getName());
        assertEquals("12345", result.get(0).getIsbn());
        assertEquals("Author 1", result.get(0).getAuthor());
        assertEquals("2002", result.get(0).getYearOfPublication());
        assertTrue(result.get(0).isAvailable());

        assertEquals("Book 2", result.get(1).getName());
        assertEquals("67890", result.get(1).getIsbn());
        assertEquals("Author 2", result.get(1).getAuthor());
        assertFalse(result.get(1).isAvailable());

        verify(bookService, times(1)).findAll();
    }

    @Test
    void testGetAllBookEmpty(){
        when(bookService.findAll()).thenReturn(Arrays.asList());
        List<Book> result = guestController.getAllBook();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookService, times(1)).findAll();
    }

    @Test
    void testGetBook(){
        when(bookService.findById(1L)).thenReturn(book3);
        Book result = guestController.getBook(1L);
        assertEquals("Book 3", result.getName());
        assertEquals("Author 3", result.getAuthor());
        assertEquals("2022", result.getYearOfPublication());
        assertFalse(result.isAvailable());
        verify(bookService, times(1)).findById(1L);
    }
}
