package com.book.bookstore.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class BooksDTO {

    @Id
    private String id;
    private String name;
    private double price;
    private String category;
    private String author;
}