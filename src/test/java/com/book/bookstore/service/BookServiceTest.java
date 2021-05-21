package com.book.bookstore.service;

import com.book.bookstore.model.BooksDTO;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    private List<BooksDTO> bookList;

    @BeforeEach
    void setUp(){
        this.bookList = new ArrayList<>();
        this.bookList.add(new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan"));
    }

    @Test
    void shouldFetchAllBooks() {

        given(bookRepository.findAll()).willReturn(bookList);

        Collection<BooksDTO> booksDTOList = bookServiceImpl.getAllBooks();

        assertEquals(booksDTOList, bookList);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldSavedBookSuccessFully(){

        final BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");

        given(bookRepository.insert(booksDTO)).willReturn(booksDTO);

        bookServiceImpl.create(booksDTO);

        verify(bookRepository, times(1)).insert(booksDTO);
    }

    @Test
    void shouldFetchIdBooks(){
        final String id = "60a41ec3b71c4bc75aab9022";
        final BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        given(bookRepository.findById(id)).willReturn(Optional.of(booksDTO));

        final BooksDTO booksDTOS = bookServiceImpl.findById(id);

        assertThat(booksDTOS).isNotNull();

        verify(bookRepository, times(1)).findById(id);
    }


    @Test
    void shouldUpdateBooks() {

        BooksDTO booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022","Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");

        given(bookRepository.save(any(BooksDTO.class))).willAnswer((invocation) -> invocation.getArgument(0));

        final BooksDTO booksDTOS = bookServiceImpl.update(booksDTO);

        assertThat(booksDTOS).isNotNull();

        verify(bookRepository).save(any(BooksDTO.class));
    }

    @Test
    void shouldDeleteBooks() {
        final String id = "60a41ec3b71c4bc75aab9022";

        bookServiceImpl.delete(id);

        verify(bookRepository, times(1)).deleteById(id);
    }
}
