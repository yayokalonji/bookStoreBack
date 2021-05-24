package com.book.bookstore.controller;

import com.book.bookstore.model.BooksDTO;
import com.book.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping( "api/books" )
public class BookController {

    @Autowired
    private BookService bookService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<BooksDTO>> getBooks(){
        logger.info("Getting data........");
        try{
            Collection<BooksDTO> collection = bookService.getAllBooks();
            if(!collection.isEmpty()){
                return ResponseEntity.ok(collection);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> saveBooks(@RequestBody BooksDTO booksDTO) {
        logger.info("Data saving..... {} ", booksDTO  );
        try{
            bookService.create(booksDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BooksDTO> updateSchool(@RequestBody BooksDTO booksDTO) {
        try{
            BooksDTO book  = bookService.update(booksDTO);
            return ResponseEntity.ok(book);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBooks(@PathVariable String id) {
        try{
            Map<String, String> map = bookService.delete(id);
            return ResponseEntity.ok(map);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<BooksDTO> searchBooks(@PathVariable String id){
        logger.info("Search data........");
        try{
            BooksDTO book = bookService.findById(id);
            if(book != null){
                return ResponseEntity.ok(book);
            }else{
                return ResponseEntity.notFound().build();
            }
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
