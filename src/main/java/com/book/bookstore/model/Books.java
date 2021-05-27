package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Data
@Document(collection = "Books")
public class Books {

    @Id
    private String id;

    private String name;

    private double price;

    private String category;

    private final String author;
}