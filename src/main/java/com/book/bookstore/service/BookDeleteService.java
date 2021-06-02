package com.book.bookstore.service;

import com.book.bookstore.model.Books;
import org.springframework.stereotype.Service;

@Service
public interface BookDeleteService {
    Books deleteBooks(String id);
}
