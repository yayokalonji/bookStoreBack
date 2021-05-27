package com.book.bookstore.controller;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping( "api/books" )
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Collection<Books>> getAllBooks() {

        Collection<Books> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<String> saveBooks(@RequestBody BooksRequest booksRequest) {
        bookService.saveBooks(booksRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

    @PutMapping
    public ResponseEntity<Books> updateBooks(@RequestBody BooksRequest booksRequest) {
        return ResponseEntity.ok(bookService.updateBooks(booksRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBooks(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBooks(id));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBooksById(id));
    }
}