package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookSaveServiceImpl bookServiceImpl;
    @Autowired
    @InjectMocks
    private BookGetAllServiceImpl bookGetAllServiceImpl;
    @Autowired
    @InjectMocks
    private BookUpdateServiceImpl updateAllServiceImpl;
    @Autowired
    @InjectMocks
    private BookDeleteServiceImpl deleteAllServiceImpl;
    @Autowired
    @InjectMocks
    private BookGetIdServiceImpl queryIdServiceImpl;

    private List<Books> bookList;
    private Books books;
    private BooksRequest booksRequest;

    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.books = new Books("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.booksRequest = new BooksRequest("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.bookList.add(books);
    }

    @Test
    void shouldFetchAllBooks(){

        given(bookRepository.findAll()).willReturn(bookList);

        Collection<Books> booksList = bookGetAllServiceImpl.getAllBooks();

        assertEquals(booksList, bookList);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldSavedBookSuccessFully(){

        given(bookRepository.insert(books)).willReturn(books);

        bookServiceImpl.saveBooks(booksRequest);

        verify(bookRepository, times(1)).insert(books);
    }

    @Test
    void shouldFetchIdBooks(){
        final String id = "60a41ec3b71c4bc75aab9022";
        given(bookRepository.findById(id)).willReturn(Optional.of(books));

        final Books booksDTOS = queryIdServiceImpl.getBooksById(id);

        assertThat(booksDTOS).isSameAs(books);

        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void shouldUpdateBooks(){

        given(bookRepository.save(this.books)).willAnswer((invocation) -> invocation.getArgument(0));

        final Books booksDTOS = updateAllServiceImpl.updateBooks(booksRequest);

        assertThat(booksDTOS).isEqualTo(this.books);

        verify(bookRepository, times(1)).save(booksDTOS);
    }

    @Test
    void shouldDeleteBooks(){

        final String id = "60a41ec3b71c4bc75aab9022";

        given(bookRepository.findById(id)).willReturn(Optional.of(books));

        Books booksDTOS = deleteAllServiceImpl.deleteBooks(id);

        assertThat(booksDTOS).isSameAs(books);
    }
}
