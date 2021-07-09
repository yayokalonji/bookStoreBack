package com.book.bookstore.util;

public enum Messages {
    NO_FOUND("Books not found"),
    FIELD_EMPTY("The field cannot be missing or empty");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
