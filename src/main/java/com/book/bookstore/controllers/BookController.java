package com.book.bookstore.controllers;

import com.book.bookstore.dtos.BooksDTO;
import com.book.bookstore.entity.BooksEntity;
import com.book.bookstore.exceptions.ApiException;
import com.book.bookstore.mappers.MapStructMapper;
import com.book.bookstore.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MapStructMapper mapStructMapper;


    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BooksEntity.class))})
    })
    @GetMapping
    public ResponseEntity<List<BooksDTO>> getAllBooks() {

        List<BooksDTO> books = bookService.getAllBooks().stream().map(booksEntity ->
                mapStructMapper.bookEntityToBookDTO(booksEntity)
        ).collect(Collectors.toList());
        logger.info("All books: {} ", books);

        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Save a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    @PostMapping
    public ResponseEntity<BooksDTO> saveBooks(@RequestBody BooksDTO booksDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapStructMapper.bookEntityToBookDTO(bookService.saveBooks(mapStructMapper.bookDTOToBookEntity(booksDTO))));
    }

    @Operation(summary = "Update a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BooksEntity.class))})
    })
    @PutMapping
    public ResponseEntity<BooksDTO> updateBooks(@RequestBody BooksDTO booksDTO) {
        return ResponseEntity.ok(mapStructMapper.bookEntityToBookDTO(bookService.updateBooks(mapStructMapper.bookDTOToBookEntity(booksDTO))));
    }


    @Operation(summary = "Delete a books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a books",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BooksEntity.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BooksEntity> deleteBooks(@PathVariable String id) throws ApiException {
        return ResponseEntity.ok(bookService.deleteBooks(id));
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BooksEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @GetMapping(path = "/{id}")
    public ResponseEntity<BooksEntity> getBooksById(@PathVariable String id) throws ApiException {
        return ResponseEntity.ok(bookService.getBooksById(id));
    }

    @Operation(summary = "Get a book by filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BooksEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/filter")
    public Page<BooksEntity> filterBooks(Pageable pageable) {
        return bookService.getBooks(pageable);
    }
}