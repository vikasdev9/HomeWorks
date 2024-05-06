package com.example.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// @Document(collation = "SignUpLoginModel")
@Document(collection = "signUpData")
public class SignUpLoginModel {

  // @Id
  // private int id;

  @NotBlank(message = "Full Name can not be empty !")
  @Size(min = 5, max = 30, message = "Name must  be between 5 To 20 characters")
  private String FullName;

  @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Please enter Valid format Email Address")
  private String EmailId;

  @Pattern(regexp = "[0-9]{10}", message = "Phone number must be 10 digits")
  private String Phone_Number;

  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String Password;

  @NotNull(message = "Gender must be specified")
  private String Gender;

  @NotBlank(message = "Street Address is required")
  private String Street_Address;

  @NotBlank(message = "Town Area is required")
  private String Town_Area;

  @NotBlank(message = "District Address is required")
  private String District;

  @NotBlank(message = "City Address is required")
  private String City;

  @NotBlank(message = "State Address is required")
  private String State;

  @Pattern(regexp = "[0-9]{6}", message = "Pincode must be 6 digits")
  private String PinCode;

  @AssertTrue(message = "Must be Agreed Condition")
  private boolean Agreed;

  private String types;

  // Parameterized constructor
  public SignUpLoginModel(String fullName, String EmailId, String phone_Number, String password, String gender,
      String street_Address, String town_Area, String district, String city, String state, String pinCode,
      boolean agreed, String type) {
    this.FullName = fullName;
    this.EmailId = EmailId;
    this.Phone_Number = phone_Number;
    this.Password = password;
    this.Gender = gender;
    this.Street_Address = street_Address;
    this.Town_Area = town_Area;
    this.District = district;
    this.City = city;
    this.State = state;
    this.PinCode = pinCode;
    this.Agreed = agreed;
    this.types = type;
  }

  // Default Constructor
  public SignUpLoginModel() {
    super();
  }

  public String getFullName() {
    return FullName;
  }

  public void setFullName(String fullName) {
    FullName = fullName;
  }

  public String getEmailId() {
    return EmailId;
  }

  public void setEmailId(String EmailId) {
    this.EmailId = EmailId;
  }

  public String getPhone_Number() {
    return Phone_Number;
  }

  public void setPhone_Number(String phone_Number) {
    Phone_Number = phone_Number;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }

  public String getGender() {
    return Gender;
  }

  public void setGender(String gender) {
    Gender = gender;
  }

  public String getStreet_Address() {
    return Street_Address;
  }

  public void setStreet_Address(String street_Address) {
    Street_Address = street_Address;
  }

  public String getTown_Area() {
    return Town_Area;
  }

  public void setTown_Area(String town_Area) {
    Town_Area = town_Area;
  }

  public String getDistrict() {
    return District;
  }

  public void setDistrict(String district) {
    District = district;
  }

  public String getCity() {
    return City;
  }

  public void setCity(String city) {
    City = city;
  }

  public String getState() {
    return State;
  }

  public void setState(String state) {
    State = state;
  }

  public String getPinCode() {
    return PinCode;
  }

  public void setPinCode(String pinCode) {
    PinCode = pinCode;
  }

  public boolean isAgreed() {
    return Agreed;
  }

  public void setAgreed(boolean agreed) {
    Agreed = agreed;
  }

  public String getType() {
    return types;
  }

  public void setType(String type) {
    this.types = type;
  }

  // public int getId() {
  // return id;
  // }

  // public void setId(int id) {
  // this.id = id;
  // }

}
