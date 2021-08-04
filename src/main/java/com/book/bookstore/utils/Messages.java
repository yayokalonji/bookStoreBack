package com.book.bookstore.utils;

public enum Messages {
    NO_FOUND("Books not found"),
    FIELD_EMPTY("The field cannot be missing or empty"),
    AUTHORIZATION("No found field 'Authorization'"),
    TOKEN_INVALID("Token is invalid"),
    USER_INVALID("This user is invalid");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
