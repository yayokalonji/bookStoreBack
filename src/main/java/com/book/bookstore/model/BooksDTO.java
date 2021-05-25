package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {

    @Id
    private String id;
    private String name;
    private double price;
    private String category;
    private String author;
}