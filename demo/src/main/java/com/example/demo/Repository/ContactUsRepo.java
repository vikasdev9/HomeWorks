package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.ContactUS;

public interface ContactUsRepo extends MongoRepository<ContactUS, String> {

}
