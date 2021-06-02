package com.book.bookstore.controller;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookSaveService bookSaveService;
    @Autowired
    private BookGetAllService bookGetAllService;
    @Autowired
    private BookUpdateService bookUpdateService;
    @Autowired
    private BookDeleteService bookDeleteService;
    @Autowired
    private BookGetIdService bookIdService;

    @GetMapping
    public ResponseEntity<Collection<Books>> getAllBooks() {

        Collection<Books> books = bookGetAllService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<String> saveBooks(@RequestBody BooksRequest booksRequest) {
        bookSaveService.saveBooks(booksRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

    @PutMapping
    public ResponseEntity<Books> updateBooks(@RequestBody BooksRequest booksRequest) {
        return ResponseEntity.ok(bookUpdateService.updateBooks(booksRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBooks(@PathVariable String id) {
        return ResponseEntity.ok(bookDeleteService.deleteBooks(id));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable String id) {
        return ResponseEntity.ok(bookIdService.getBooksById(id));
    }
}