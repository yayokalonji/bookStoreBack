package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    Collection<Books> getAllBooks();

    void saveBooks(BooksRequest booksRequest);

    Books updateBooks(BooksRequest booksRequest);

    Books deleteBooks(String id);

    Books getBooksById(String id);
}
