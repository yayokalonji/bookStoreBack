package com.book.bookstore.service.impl;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private final Map<Long, Books> booksMap = new HashMap<>();

    @Override
    public void saveBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getAuthor(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory());
        bookRepository.insert(books);
    }

    @Override
    public Collection<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books getBooksById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Books updateBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getAuthor(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory());
        return bookRepository.save(books);
    }

    @Override
    public Books deleteBooks(String id) {
        Books books = bookRepository.findById(id).orElse(null);
        if (books != null) {
            bookRepository.delete(books);
        }
        return books;
    }

    @Override
    public Page<Books> getBooks(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<Books> result = this.booksMap.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, this.booksMap.size());
    }
}