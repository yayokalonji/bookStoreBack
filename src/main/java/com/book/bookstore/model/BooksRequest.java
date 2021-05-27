package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BooksRequest {

    private String id;

    private String name;

    private double price;

    private String category;

    private String author;
}
