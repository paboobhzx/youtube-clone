package com.pablo.portfolio.youtubeclone.repository;

import com.pablo.portfolio.youtubeclone.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
