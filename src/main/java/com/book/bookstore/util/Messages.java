package com.book.bookstore.util;

public enum Messages {
    NO_FOUND("Books not found");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
