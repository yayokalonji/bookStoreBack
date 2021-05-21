package com.book.bookstore.service.impl;

import com.book.bookstore.model.BooksDTO;
import com.book.bookstore.repository.BookRepository;
import com.book.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Collection<BooksDTO> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void create(BooksDTO booksDTO) {
        bookRepository.insert(booksDTO);
    }

    @Override
    public BooksDTO update(BooksDTO booksDTO) {
        return bookRepository.save(booksDTO);
    }

    @Override
    public Map<String, String> delete(String id) {

        long beforeDelete = bookRepository.count();

        bookRepository.deleteById(id);

        long afterDelete = bookRepository.count();

        String messageValue  = beforeDelete == afterDelete ? "Something went wrong!" : "Record deleted";

        Map<String, String> deleteMap = new HashMap<>();
        deleteMap.put("message", messageValue);

        return deleteMap;
    }

    @Override
    public BooksDTO findById(String id) {
        return bookRepository.findById(id).orElse(new BooksDTO());
    }
}
