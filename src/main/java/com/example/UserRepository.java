package com.example;

import io.micronaut.data.mongodb.annotation.MongoFindQuery;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.CrudRepository;
import org.bson.types.ObjectId;

import java.util.Optional;

@MongoRepository
public interface UserRepository extends CrudRepository<User, ObjectId> {

    @MongoFindQuery("{email: {\"$regex\": :email, \"$options\": \"i\"}}")
    Optional<User> getUserByEmailCaseInsensitive(String email);

    Optional<User> findByEmailRegex(String email);
}
