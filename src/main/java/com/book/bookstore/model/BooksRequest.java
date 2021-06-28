package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

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
    @NonNull
    @NotBlank
    private String author;
}
