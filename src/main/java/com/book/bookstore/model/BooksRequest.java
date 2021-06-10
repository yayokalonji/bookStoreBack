package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class BooksRequest {

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private double price;
    @NotBlank
    private String category;
    @NotBlank
    private String author;
}
