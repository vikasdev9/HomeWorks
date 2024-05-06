package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.example.demo.Model.SignUpLoginModel;

public interface SignUpLoginRepo extends MongoRepository<SignUpLoginModel, String> {
  @Query("{ 'EmailId' : ?0 }")
  SignUpLoginModel findByEmailId(String EmailId);
}
