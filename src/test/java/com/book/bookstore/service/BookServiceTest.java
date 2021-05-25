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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookServiceImpl bookServiceImpl;
    private List<BooksDTO> bookList;
    private BooksDTO booksDTO;

    @BeforeEach
    void setUp(){
        this.bookList = new ArrayList<>();
        this.booksDTO = new BooksDTO("60a41ec3b71c4bc75aab9022", "Against Democracy: New Preface", 18.95, "Political", "Jason Brennan");
        this.bookList.add(booksDTO);
    }

    @Test
    void shouldFetchAllBooks(){

        given(bookRepository.findAll()).willReturn(bookList);

        Collection<BooksDTO> booksDTOList = bookServiceImpl.getAllBooks();

        assertEquals(booksDTOList, bookList);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldSavedBookSuccessFully(){

        given(bookRepository.insert(booksDTO)).willReturn(booksDTO);

        bookServiceImpl.saveBooks(booksDTO);

        verify(bookRepository, times(1)).insert(booksDTO);
    }

    @Test
    void shouldFetchIdBooks(){
        final String id = "60a41ec3b71c4bc75aab9022";
        given(bookRepository.findById(id)).willReturn(Optional.of(booksDTO));

        final BooksDTO booksDTOS = bookServiceImpl.getBooksById(id);

        assertThat(booksDTOS).isSameAs(booksDTO);

        verify(bookRepository, times(1)).findById(id);
    }


    @Test
    void shouldUpdateBooks(){

        given(bookRepository.save(booksDTO)).willAnswer((invocation) -> invocation.getArgument(0));

        final BooksDTO booksDTOS = bookServiceImpl.updateBooks(booksDTO);

        assertThat(booksDTOS).isSameAs(booksDTO);

        verify(bookRepository, times(1)).save(booksDTOS);
    }



    @Test
    void shouldDeleteBooks(){

        final String id = "60a41ec3b71c4bc75aab9022";

        given(bookRepository.findById(id)).willReturn(Optional.of(booksDTO));

        bookServiceImpl.deleteBooks(id);

        verify(bookRepository, times(1)).deleteById(id);
    }
}
