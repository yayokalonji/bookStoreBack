package com.book.bookstore.service.impl;

import com.book.bookstore.exception.ApiException;
import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookService;
import com.book.bookstore.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private final Map<Long, Books> booksMap = new HashMap<>();

    @Override
    public Books saveBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getAuthor(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory());
        return bookRepository.save(books);
    }

    @Override
    public Collection<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books getBooksById(String id) throws ApiException {
        final Optional<Books> booksOptional = bookRepository.findById(id);
        return booksOptional.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, Messages.NO_FOUND.getMessage()));
    }

    @Override
    public Books updateBooks(BooksRequest booksRequest) {
        Books books = new Books(booksRequest.getId(), booksRequest.getAuthor(), booksRequest.getName(), booksRequest.getPrice(), booksRequest.getCategory());
        return bookRepository.save(books);
    }

    @Override
    public Books deleteBooks(String id) throws ApiException {
        Books books = bookRepository.findById(id).orElse(null);
        if (books == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, Messages.NO_FOUND.getMessage());
        }
        bookRepository.delete(books);
        return books;
    }

    @Override
    public Page<Books> getBooks(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<Books> result = this.booksMap.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, this.booksMap.size());
    }
}