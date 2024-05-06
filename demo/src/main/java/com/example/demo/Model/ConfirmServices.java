package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ConfirmServices")
public class ConfirmServices {
  private String WorkerName;
  private String Service_Name;
  private String WorkerEmailId;
  private String Day_of_Booking;
  private String Money_Charge;
  private String Hour_Rate;
  private String UserName;
  private String userEmailId;
  private String UserContact;

  public ConfirmServices(String workerName, String service_Name, String setWorkerEmailId, String day_of_Booking,
      String money_Charge, String hour_Rate, String userName, String userEmailId, String userContact) {
    WorkerName = workerName;
    Service_Name = service_Name;
    this.WorkerEmailId = setWorkerEmailId;
    Day_of_Booking = day_of_Booking;
    Money_Charge = money_Charge;
    Hour_Rate = hour_Rate;
    UserName = userName;
    this.userEmailId = userEmailId;
    UserContact = userContact;
  }

  public ConfirmServices() {
  }

  public String getWorkerName() {
    return WorkerName;
  }

  public void setWorkerName(String workerName) {
    WorkerName = workerName;
  }

  public String getService_Name() {
    return Service_Name;
  }

  public void setService_Name(String service_Name) {
    Service_Name = service_Name;
  }

  public String getSetWorkerEmailId() {
    return WorkerEmailId;
  }

  public void setSetWorkerEmailId(String setWorkerEmailId) {
    this.WorkerEmailId = setWorkerEmailId;
  }

  public String getDay_of_Booking() {
    return Day_of_Booking;
  }

  public void setDay_of_Booking(String day_of_Booking) {
    Day_of_Booking = day_of_Booking;
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

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }

  public String getUserEmailId() {
    return userEmailId;
  }

  public void setUserEmailId(String userEmailId) {
    this.userEmailId = userEmailId;
  }

  public String getUserContact() {
    return UserContact;
  }

  public void setUserContact(String userContact) {
    UserContact = userContact;
  }

}
