package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookUpdateService {
    Books updateBooks(BooksRequest booksRequest);
}
