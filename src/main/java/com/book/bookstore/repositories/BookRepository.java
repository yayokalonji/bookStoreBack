package com.book.bookstore.repositories;

import com.book.bookstore.entity.BooksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BooksEntity, String> {
}
