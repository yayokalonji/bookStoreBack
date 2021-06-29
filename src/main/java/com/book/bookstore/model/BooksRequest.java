package com.book.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class BooksRequest implements Serializable {

    private static final long serialVersionUID = -894293290677093328L;

    @NotBlank
    private String id;
    @NonNull
    @NotBlank
    private String name;
    @NotBlank
    private double price;
    @NonNull
    @NotBlank
    private String category;
    @NonNull
    @NotBlank
    private String author;
}
