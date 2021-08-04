package com.book.bookstore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String userName;
    private String password;
    private String token;
}
