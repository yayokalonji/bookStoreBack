package com.book.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Books")
public class BooksEntity implements Serializable {

    private static final long serialVersionUID = 863059907274189363L;

    @Id
    private String id;
    private String author;
    private String name;
    private double price;
    private String category;

    public BooksEntity(String author, String name, double price, String category) {
        this.author = author;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}