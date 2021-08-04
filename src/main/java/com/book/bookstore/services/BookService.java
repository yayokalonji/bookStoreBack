package com.book.bookstore.services;

import com.book.bookstore.entity.BooksEntity;
import com.book.bookstore.exceptions.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    BooksEntity saveBooks(BooksEntity booksEntity);

    List<BooksEntity> getAllBooks();

    BooksEntity getBooksById(String id) throws ApiException;

    BooksEntity updateBooks(BooksEntity booksEntity);

    BooksEntity deleteBooks(String id) throws ApiException;

    Page<BooksEntity> getBooks(Pageable pageable);
}
