package com.book.bookstore.service;

import com.book.bookstore.model.BooksDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface BookService {

    Collection<BooksDTO> getAllBooks();

    void saveBooks(BooksDTO booksDTO);

    BooksDTO updateBooks(BooksDTO booksDTO);

    BooksDTO deleteBooks (String id);

    BooksDTO getBooksById(String id);
}
