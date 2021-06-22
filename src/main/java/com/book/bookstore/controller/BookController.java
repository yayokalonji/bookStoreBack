package com.book.bookstore.controller;

import com.book.bookstore.model.Books;
import com.book.bookstore.model.BooksRequest;
import com.book.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Books.class))})
    })
    @GetMapping
    public ResponseEntity<Collection<Books>> getAllBooks() {

        Collection<Books> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Save a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping
    public ResponseEntity<Books> saveBooks(@RequestBody BooksRequest booksRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBooks(booksRequest));
    }

    @Operation(summary = "Update a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Books.class))})
    })
    @PutMapping
    public ResponseEntity<Books> updateBooks(@RequestBody BooksRequest booksRequest) {
        return ResponseEntity.ok(bookService.updateBooks(booksRequest));
    }


    @Operation(summary = "Delete a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Books.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBooks(@PathVariable String id) {
        return ResponseEntity.ok(bookService.deleteBooks(id));
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Books.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @GetMapping(path = "/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable String id) {
        return ResponseEntity.ok(bookService.getBooksById(id));
    }

    @Operation(summary = "Get a book by filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Books.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/filter")
    public Page<Books> filterBooks(Pageable pageable) {
        return bookService.getBooks(pageable);
    }
}