package com.book.bookstore.controller;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BookController.class)
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;
    private List<Books> bookList;
    private Books books;
    private BooksRequest booksRequest;

    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.books = new Books("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.booksRequest = new BooksRequest("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.bookList.add(this.books);
    }

    @Test
    void getAllBooks() throws Exception {

        given(bookService.getAllBooks()).willReturn(bookList);

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())));
    }

    @Test
    void shouldFetchOneBooksById() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        this.books = new Books("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookService.getBooksById(id)).willReturn(books);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldNotFoundBooksById() throws Exception {
        final String id = "1";
        given(bookService.getBooksById(id)).willReturn(null);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldCreateNewBook() throws Exception {

        this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(this.booksRequest)))
                .andExpect(status().isCreated());

        doNothing().when(bookService).saveBooks(this.booksRequest);
    }

    @Test
    void shouldUpdateBooks() throws Exception {
        BooksRequest booksRequest = new BooksRequest("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookService.updateBooks(booksRequest)).willReturn(this.books);

        this.mockMvc.perform(put("/api/books/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(this.books)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteBooks() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        given(bookService.deleteBooks(id)).willReturn(this.books);

        this.mockMvc.perform(delete("/api/books/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchAllNotFoundBooks() throws Exception {

        Collection<Books> books = new ArrayList<>();
        given(bookService.getAllBooks()).willReturn(books);

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }
}
