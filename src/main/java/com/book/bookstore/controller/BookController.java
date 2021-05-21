package com.book.bookstore.controller;

import com.book.bookstore.model.BooksDTO;
import com.book.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public Collection<BooksDTO> getBooks(){
        logger.info("Getting data........");
        return bookService.getAllBooks();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveSchool(@RequestBody BooksDTO booksDTO) {
        logger.info("Data saving..... {} ", booksDTO  );
        bookService.create(booksDTO);
        return "Ok";
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BooksDTO updateSchool(@RequestBody BooksDTO booksDTO) {
        return bookService.update(booksDTO);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteBooks(@PathVariable String id) {
        return bookService.delete(id);
    }

    @GetMapping(path ="/{id}")
    public BooksDTO searchBooks(@PathVariable String id){
        logger.info("Search data........");
        return bookService.findById(id);
    }
}
