package com.book.bookstore.service.impl;

import com.book.bookstore.model.Books;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookGetAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookGetAllServiceImpl implements BookGetAllService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Books> getAllBooks() {
        return bookRepository.findAll();
    }
}
