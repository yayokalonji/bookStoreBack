package com.book.bookstore.controllers;

import com.book.bookstore.SpringSecurityWebAuxTestConfig;
import com.book.bookstore.dtos.BooksDTO;
import com.book.bookstore.entity.BooksEntity;
import com.book.bookstore.exceptions.ApiException;
import com.book.bookstore.mappers.MapStructMapper;
import com.book.bookstore.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MapStructMapper mapStructMapper;

    private List<BooksEntity> bookList;
    private BooksEntity booksEntity;
    private BooksDTO booksDTO;


    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.booksEntity = new BooksEntity("60a41ec3b71c4bc75aab9022", "Jason Brennan", "Against Democracy: New Preface", 18.95, "Jason Brennan");
        this.booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.bookList.add(this.booksEntity);
    }

    @Test
    @WithMockUser(username = "admin")
    void getAllBooks() throws Exception {

        given(bookService.getAllBooks()).willReturn(bookList);

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())));
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldFetchOneBooksById() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        this.booksEntity = new BooksEntity("60a41ec3b71c4bc75aab9022", "Jason Brennan", "Against Democracy: New Preface", 18.95, "Jason Brennan");
        given(bookService.getBooksById(id)).willReturn(booksEntity);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldNotFoundBooksById() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9065";
        given(bookService.getBooksById(id)).willReturn(null);

        this.mockMvc.perform(get("/api/books/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldCreateNewBook() throws Exception {

        given(bookService.saveBooks(mapStructMapper.bookDTOToBookEntity(this.booksDTO))).willReturn(this.booksEntity);

        this.mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(this.booksDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldUpdateBooks() throws Exception {
        BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookService.updateBooks(mapStructMapper.bookDTOToBookEntity(booksDTO))).willReturn(this.booksEntity);

        this.mockMvc.perform(put("/api/books/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(this.booksEntity)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldDeleteBooks() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        given(bookService.deleteBooks(id)).willReturn(this.booksEntity);

        this.mockMvc.perform(delete("/api/books/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldFetchAllNotFoundBooks() throws Exception {

        List<BooksEntity> books = new ArrayList<>();
        given(bookService.getAllBooks()).willReturn(books);

        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void shouldFetchFilter() throws Exception {

        PageRequest pagingRequest = PageRequest.of(1, 10);
        Page<BooksEntity> booksPage = new PageImpl<>(this.bookList, pagingRequest, this.bookList.size());

        given(bookService.getBooks(Mockito.any(PageRequest.class))).willReturn(booksPage);

        this.mockMvc.perform(get("/api/books/filter?page=1&size=10"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin")
    void whenDerivedExceptionThrown_thenAssertionSucceds() {
        ApiException exception = assertThrows(ApiException.class, () -> {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Bad request");
        });

        String expectedMessage = "{reason=Bad Request, code=400, message=Bad request}";
        String actualMessage = exception.getJsonMessage().toString();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    /*@Test
    void whenDerivedExceptionThrownSign_thenAssertionSucceds() {

        String tokenUpdate = this.token.concat("s");

        assertThatExceptionOfType(SignatureException.class)
                .isThrownBy(() -> this.mockMvc.perform(get("/api/books").header("Authorization", tokenUpdate)))
                .withMessage("JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.");
    }*/

    /*@Test
    void whenDerivedExceptionThrownTokenBearer_thenAssertionSucceds() throws Exception {

        this.mockMvc.perform(get("/api/books").header("Authorization", this.tokenBearer))
                .andExpect(status().is4xxClientError());
    }*/
}
