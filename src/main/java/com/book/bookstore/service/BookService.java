package com.book.bookstore.service;

import com.book.bookstore.exception.ApiException;
import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    Books saveBooks(BooksRequest booksRequest);

    Collection<Books> getAllBooks();

    Books getBooksById(String id) throws ApiException;

    Books updateBooks(BooksRequest booksRequest);

    Books deleteBooks(String id);

    Page<Books> getBooks(Pageable pageable);

}
