package com.book.bookstore.repository;

import com.book.bookstore.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Books, String> {
}
