package com.book.bookstore.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class User {

    @NotBlank
    @NonNull
    private String userName;
    @NotBlank
    @NonNull
    private String password;
    private String token;
}
