package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookGetAllService {

    Collection<Books> getAllBooks();
}
