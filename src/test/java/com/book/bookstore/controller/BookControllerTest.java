package com.book.bookstore.controller;

import com.book.bookstore.model.BooksDTO;
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
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = BookController.class)
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<BooksDTO> bookList;

    @BeforeEach
    void setUp(){
        this.bookList = new ArrayList<>();
        this.bookList.add(new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan"));
    }

    @Test
    void shouldFetchAllBooks() throws Exception {

        given(bookService.getAllBooks()).willReturn(bookList);

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())));
    }

    @Test
    void shouldFetchOneBooksById() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        final BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookService.findById(id)).willReturn(booksDTO);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void shouldNotFoundBooksById() throws Exception {
        final String id = "1";
        given(bookService.findById(id)).willReturn(null);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewBook() throws Exception {

        BooksDTO booksDTO = new BooksDTO("1","Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");

        doNothing().when(bookService).create(booksDTO);

        this.mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(booksDTO)))
                .andExpect(status().isCreated());

        verify(bookService).create(booksDTO);
    }

    @Test
    void shouldUpdateBooks() throws Exception {
        BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022","Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookService.findById(booksDTO.getId())).willReturn(booksDTO);
        given(bookService.update(booksDTO)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/books/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(booksDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteBooks() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        this.mockMvc.perform(delete("/api/books/{id}", id))
                .andExpect(status().isOk());
    }
}
