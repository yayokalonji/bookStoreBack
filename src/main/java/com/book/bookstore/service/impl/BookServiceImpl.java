package com.book.bookstore.service.impl;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory(), booksRequest.getAuthor());
        bookRepository.insert(books);
    }

    @Override
    public Books updateBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory(), booksRequest.getAuthor());
        return bookRepository.save(books);
    }

    @Override
    public Books deleteBooks(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Books getBooksById(String id) {
        return bookRepository.findById(id).orElse(null);
    }
}