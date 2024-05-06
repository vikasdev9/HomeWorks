package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.Model.Booking;

public interface BookedRepo extends MongoRepository<Booking, String> {

  @Query("{ 'workerEmailId' : ?0 }")
  Booking findByUniqueEmailId(String workerEmailId);

}
