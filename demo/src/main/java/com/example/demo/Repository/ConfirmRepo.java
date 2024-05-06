package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.ConfirmServices;

public interface ConfirmRepo extends MongoRepository<ConfirmServices, String> {

}
