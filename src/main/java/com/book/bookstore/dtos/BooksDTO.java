package com.book.bookstore.dtos;

import com.book.bookstore.entity.BooksEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksDTO {

    private String id;
    private String name;
    private double price;
    private String category;
    private String author;

    public BooksDTO(BooksEntity booksEntity) {
        this.id = booksEntity.getId();
        this.name = booksEntity.getName();
        this.price = booksEntity.getPrice();
        this.category = booksEntity.getCategory();
        this.author = booksEntity.getAuthor();
    }
}
