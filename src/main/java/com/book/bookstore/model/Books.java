package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document(collection = "Books")
public class Books {

    @Id
    private String id;
    private String author;
    private String name;
    private double price;
    private String category;

    public Books(String author, String name, double price, String category) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Books() {
    }
}