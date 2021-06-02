package com.book.bookstore.service.impl;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookUpdateServiceImpl implements BookUpdateService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Books updateBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory(), booksRequest.getAuthor());
        return bookRepository.save(books);
    }
}
