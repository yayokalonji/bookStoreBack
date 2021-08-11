package com.book.bookstore.repositories;

import com.book.bookstore.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByUsername(String username);
}
