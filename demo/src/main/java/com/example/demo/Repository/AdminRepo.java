package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Model.Adminlogin;

public interface AdminRepo extends MongoRepository<Adminlogin, String> {
  @Query("{ 'AdminEmail' : ?0 }")
  Adminlogin findByAdminEmail(String AdminEmail);
}
