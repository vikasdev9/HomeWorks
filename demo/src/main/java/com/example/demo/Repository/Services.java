package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Model.Worker_Service;

public interface Services extends MongoRepository<Worker_Service, String> {
  @Query("{ 'Service_Name' : ?0 }")
  Worker_Service findByService_Name(String Service_Name);
}
