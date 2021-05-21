package com.book.bookstore.repository;

import com.book.bookstore.model.BooksDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BooksDTO, String> { }
