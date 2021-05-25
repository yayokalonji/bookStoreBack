package com.book.bookstore.service.impl;

import com.book.bookstore.model.BooksDTO;
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
    public Collection<BooksDTO> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBooks(BooksDTO booksDTO){
        bookRepository.insert(booksDTO);
    }

    @Override
    public BooksDTO updateBooks(BooksDTO booksDTO){
        return bookRepository.save(booksDTO);
    }

    @Override
    public BooksDTO deleteBooks(String id) {
        BooksDTO booksDTO = bookRepository.findById(id).orElse(null);

       if (booksDTO != null){
           bookRepository.deleteById(id);
       }
       return booksDTO;
    }

    @Override
    public BooksDTO getBooksById(String id){
        return bookRepository.findById(id).orElse(null);
    }
}