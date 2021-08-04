package com.book.bookstore.services.impl;

import com.book.bookstore.entity.BooksEntity;
import com.book.bookstore.exceptions.ApiException;
import com.book.bookstore.repositories.BookRepository;
import com.book.bookstore.services.BookService;
import com.book.bookstore.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private final Map<Long, BooksEntity> booksMap = new HashMap<>();

    @Override
    public BooksEntity saveBooks(BooksEntity booksEntity) {
        return bookRepository.save(booksEntity);
    }

    @Override
    public List<BooksEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public BooksEntity getBooksById(String id) throws ApiException {
        final Optional<BooksEntity> booksOptional = bookRepository.findById(id);
        return booksOptional.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, Messages.NO_FOUND.getMessage()));
    }

    @Override
    public BooksEntity updateBooks(BooksEntity booksEntity) {
        return bookRepository.save(booksEntity);
    }

    @Override
    public BooksEntity deleteBooks(String id) throws ApiException {
        BooksEntity booksEntity = bookRepository.findById(id).orElse(null);
        if (booksEntity == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, Messages.NO_FOUND.getMessage());
        }
        bookRepository.delete(booksEntity);
        return booksEntity;
    }

    @Override
    public Page<BooksEntity> getBooks(Pageable pageable) {
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        List<BooksEntity> result = this.booksMap.values().stream().skip(toSkip).limit(pageable.getPageSize()).collect(toList());

        return new PageImpl<>(result, pageable, this.booksMap.size());
    }
}