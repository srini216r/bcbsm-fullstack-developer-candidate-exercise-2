package com.user.UserApplication.repository;

import com.user.UserApplication.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    // You can define custom query methods here if needed.
}
