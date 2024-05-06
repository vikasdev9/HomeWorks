package com.example.demo.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "Services")
public class Worker_Service {

  @NotBlank(message = "Field can't be empty")
  @Size(min = 4, max = 20)
  private String Service_Name;

  @NotBlank(message = "This field cannot be empty")
  private String Hour;

  @NotBlank(message = "This field cannot be empty")
  private String Money_charge;

  @NotBlank(message = "This field cannot be empty")
  private String Available_day;

  @NotBlank(message = "This field cannot be empty")
  private String description;

  @NotBlank(message = "This field cannot be empty")
  private String Experience;

  private String EmailId;

  private String workerContact;

  public Worker_Service(String service_Name,
      String hour,
      String money_charge,
      String available_day,
      String description,
      String experience, String emailId, String workerContact) {
    Service_Name = service_Name;
    Hour = hour;
    Money_charge = money_charge;
    Available_day = available_day;
    this.description = description;
    Experience = experience;
    EmailId = emailId;
    this.workerContact = workerContact;
  }

  public Worker_Service() {
  }

  public String getService_Name() {
    return Service_Name;
  }

  public void setService_Name(String service_Name) {
    Service_Name = service_Name;
  }

  public String getHour() {
    return Hour;
  }

  public void setHour(String hour) {
    Hour = hour;
  }

  public String getMoney_charge() {
    return Money_charge;
  }

  public void setMoney_charge(String money_charge) {
    Money_charge = money_charge;
  }

  public String getAvailable_day() {
    return Available_day;
  }

  public void setAvailable_day(String available_day) {
    Available_day = available_day;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getExperience() {
    return Experience;
  }

  public void setExperience(String experience) {
    Experience = experience;
  }

  public String getEmailId() {
    return EmailId;
  }

  public void setEmailId(String emailId) {
    EmailId = emailId;
  }

  public String getWorkerContact() {
    return workerContact;
  }

  public void setWorkerContact(String workerContact) {
    this.workerContact = workerContact;
  }

}
