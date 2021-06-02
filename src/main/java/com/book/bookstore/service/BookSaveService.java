package com.book.bookstore.service;

import com.book.bookstore.model.BooksRequest;
import org.springframework.stereotype.Service;

@Service
public interface BookSaveService {
    void saveBooks(BooksRequest booksRequest);
}
