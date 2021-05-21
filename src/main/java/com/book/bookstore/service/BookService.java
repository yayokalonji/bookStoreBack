package com.book.bookstore.service;

import com.book.bookstore.model.BooksDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public interface BookService {

    Collection<BooksDTO> getAllBooks();

    void create(BooksDTO booksDTO);

    BooksDTO update(BooksDTO booksDTO);

    Map<String, String> delete (String id);

    BooksDTO findById(String id);
}
