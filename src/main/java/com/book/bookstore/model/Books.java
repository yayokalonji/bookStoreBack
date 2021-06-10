package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Data
@Document(collection = "Books")
public class Books {

    @Id
    private String id;

    @NotBlank
    private final String author;
    @NotBlank
    private String name;
    @NotBlank
    private double price;
    @NotBlank
    private String category;
}