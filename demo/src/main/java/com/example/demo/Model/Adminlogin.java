package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "Adminlogin")
public class Adminlogin {
  @NotBlank(message = "Please  enter Email.")
  private String AdminEmail;

  @NotBlank(message = "Please enter valid password")
  private String Adminpassword;

  public Adminlogin() {
  }

  public Adminlogin(@NotBlank(message = "Please  enter Email.") String adminEmail,
      @NotBlank(message = "Please enter valid password") String adminpassword) {
    AdminEmail = adminEmail;
    Adminpassword = adminpassword;
  }

  public String getAdminEmail() {
    return AdminEmail;
  }

  public void setAdminEmail(String adminEmail) {
    AdminEmail = adminEmail;
  }

  public String getAdminpassword() {
    return Adminpassword;
  }

  public void setAdminpassword(String adminpassword) {
    Adminpassword = adminpassword;
  }

}
