package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Booking")
public class Booking {
  private String userName;
  private String workerName;
  private String Usercontact;
  private String WorkerContact;
  private String userEmailId;
  private String workerEmailId;
  private String servicetypes;
  private String Money_Charge;
  private String Hour_Rate;
  private String DayofBooking;
  private String userAddress;
  private String userCity;
  private String landmarks;
  private String userPinCode;
  private String Status;

  public Booking() {
  }

  public Booking(String userName, String workerName, String usercontact, String workerContact, String userEmailId,
      String workerEmailId, String servicetypes, String money_Charge, String hour_Rate, String dayofBooking,
      String userAddress, String userCity, String landmarks, String userPinCode, String status) {
    this.userName = userName;
    this.workerName = workerName;
    Usercontact = usercontact;
    WorkerContact = workerContact;
    this.userEmailId = userEmailId;
    this.workerEmailId = workerEmailId;
    this.servicetypes = servicetypes;
    Money_Charge = money_Charge;
    Hour_Rate = hour_Rate;
    DayofBooking = dayofBooking;
    this.userAddress = userAddress;
    this.userCity = userCity;
    this.landmarks = landmarks;
    this.userPinCode = userPinCode;
    Status = status;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getWorkerName() {
    return workerName;
  }

  public void setWorkerName(String workerName) {
    this.workerName = workerName;
  }

  public String getUsercontact() {
    return Usercontact;
  }

  public void setUsercontact(String usercontact) {
    Usercontact = usercontact;
  }

  public String getWorkerContact() {
    return WorkerContact;
  }

  public void setWorkerContact(String workerContact) {
    WorkerContact = workerContact;
  }

  public String getUserEmailId() {
    return userEmailId;
  }

  public void setUserEmailId(String userEmailId) {
    this.userEmailId = userEmailId;
  }

  public String getWorkerEmailId() {
    return workerEmailId;
  }

  public void setWorkerEmailId(String workerEmailId) {
    this.workerEmailId = workerEmailId;
  }

  public String getServicetypes() {
    return servicetypes;
  }

  public void setServicetypes(String servicetypes) {
    this.servicetypes = servicetypes;
  }

  public String getMoney_Charge() {
    return Money_Charge;
  }

  public void setMoney_Charge(String money_Charge) {
    Money_Charge = money_Charge;
  }

  public String getHour_Rate() {
    return Hour_Rate;
  }

  public void setHour_Rate(String hour_Rate) {
    Hour_Rate = hour_Rate;
  }

  public String getDayofBooking() {
    return DayofBooking;
  }

  public void setDayofBooking(String dayofBooking) {
    DayofBooking = dayofBooking;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public String getUserCity() {
    return userCity;
  }

  public void setUserCity(String userCity) {
    this.userCity = userCity;
  }

  public String getLandmarks() {
    return landmarks;
  }

  public void setLandmarks(String landmarks) {
    this.landmarks = landmarks;
  }

  public String getUserPinCode() {
    return userPinCode;
  }

  public void setUserPinCode(String userPinCode) {
    this.userPinCode = userPinCode;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String status) {
    Status = status;
  }

}
