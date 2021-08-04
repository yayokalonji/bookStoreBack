package com.book.bookstore.services;

import com.book.bookstore.entity.BooksEntity;
import com.book.bookstore.exceptions.ApiException;
import com.book.bookstore.repositories.BookRepository;
import com.book.bookstore.services.impl.BookServiceImpl;
import com.book.bookstore.utils.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookServiceImpl bookServiceImpl;


    private List<BooksEntity> bookList;
    private BooksEntity booksEntity, booksEntityInsert;

    @BeforeEach
    void setUp() {
        this.bookList = new ArrayList<>();
        this.booksEntity = new BooksEntity("60a41ec3b71c4bc75aab9022", "Jason Brennan", "Against Democracy: New Preface", 18.95, "Political");
        this.bookList.add(booksEntity);
    }

    @Test
    void shouldFetchAllBooks() {

        given(bookRepository.findAll()).willReturn(bookList);

        Collection<BooksEntity> booksEntityList = bookServiceImpl.getAllBooks();

        assertEquals(booksEntityList, bookList);

        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldSavedBookSuccessFully() {

        given(bookRepository.save(this.booksEntity)).willReturn(this.booksEntity);

        bookServiceImpl.saveBooks(this.booksEntity);

        verify(bookRepository, times(1)).save(this.booksEntity);
    }

    @Test
    void shouldFetchIdBooks() throws Exception {
        final String id = "60a41ec3b71c4bc75aab9022";
        given(bookRepository.findById(id)).willReturn(Optional.of(booksEntity));

        final BooksEntity booksEntityDTOS = bookServiceImpl.getBooksById(id);

        assertThat(booksEntityDTOS).isSameAs(booksEntity);

        verify(bookRepository, times(1)).findById(id);
    }

    @Test
    void shouldUpdateBooks() {

        given(bookRepository.save(this.booksEntity)).willAnswer((invocation) -> invocation.getArgument(0));

        final BooksEntity booksEntityDTOS = bookServiceImpl.updateBooks(this.booksEntity);

        assertThat(booksEntityDTOS).isEqualTo(this.booksEntity);

        verify(bookRepository, times(1)).save(booksEntityDTOS);
    }

    @Test
    void shouldDeleteBooks() throws Exception {

        final String id = "60a41ec3b71c4bc75aab9022";

        given(bookRepository.findById(id)).willReturn(Optional.of(booksEntity));

        BooksEntity booksEntityDTOS = bookServiceImpl.deleteBooks(id);

        assertThat(booksEntityDTOS).isSameAs(booksEntity);
    }

    @Test
    void shouldFetchFilter() {
        PageRequest pagingRequest = PageRequest.of(1, 10);

        Page<BooksEntity> booksDTOS = bookServiceImpl.getBooks(pagingRequest);

        assertThat(booksDTOS).doesNotContainNull();
    }

    @Test
    void shouldFetchIdBooksException() {
        assertThrows(Exception.class, () -> {
            throw new ApiException(HttpStatus.BAD_REQUEST, Messages.NO_FOUND.getMessage());
        });
    }

    @Test
    void shouldDeleteBooksNull() {

        final String id = "1";
        assertThrows(Exception.class, () -> bookServiceImpl.deleteBooks(id));
    }
}
