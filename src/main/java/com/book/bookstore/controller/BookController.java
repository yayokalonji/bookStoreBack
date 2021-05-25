package com.book.bookstore.controller;

import com.book.bookstore.model.BooksDTO;
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
    public ResponseEntity<Collection<BooksDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<String> saveBooks(@RequestBody BooksDTO booksDTO) {
        bookService.saveBooks(booksDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
    }

    @PutMapping
    public ResponseEntity<BooksDTO> updateBooks(@RequestBody BooksDTO booksDTO) {
        BooksDTO book  = bookService.updateBooks(booksDTO);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BooksDTO> deleteBooks(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBooks(id));
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<BooksDTO> getBooksById(@PathVariable String id){
        return ResponseEntity.ok(bookService.getBooksById(id));
    }
}
