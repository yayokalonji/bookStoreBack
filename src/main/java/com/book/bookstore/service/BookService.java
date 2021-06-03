package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    void saveBooks(BooksRequest booksRequest);

    Collection<Books> getAllBooks();

    Books getBooksById(String id);

    Books updateBooks(BooksRequest booksRequest);

    Books deleteBooks(String id);
}
