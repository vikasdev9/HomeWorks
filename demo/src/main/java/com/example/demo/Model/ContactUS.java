package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Document(collection = "AdminContact")
public class ContactUS {

  @NotBlank(message = "Full Name can not be empty !")
  private String name;

  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter Valid format Email Address")
  private String Email;

  @NotBlank(message = "Please Enter Your Query !")
  private String Message;

  public ContactUS() {
  }

  public ContactUS(String name, String email, String message) {
    this.name = name;
    Email = email;
    Message = message;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

}
