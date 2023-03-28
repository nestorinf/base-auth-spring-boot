package com.base.auth.repository;

import com.base.auth.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // methods
    Optional<User> findByUsername(String username);
}
