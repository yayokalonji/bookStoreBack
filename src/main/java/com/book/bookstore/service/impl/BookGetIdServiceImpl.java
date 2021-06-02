package com.book.bookstore.service.impl;

import com.book.bookstore.model.Books;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookGetIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookGetIdServiceImpl implements BookGetIdService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Books getBooksById(String id) {
        return bookRepository.findById(id).orElse(null);
    }
}
