package com.book.bookstore.repositories;

import com.book.bookstore.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUsername(String username);
}
