package com.example.demo.Model;

import java.util.List;

public class UserServiceData {

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Worker_Service> getServicesData() {
    return servicesData;
  }

  public void setServicesData(List<Worker_Service> servicesData) {
    this.servicesData = servicesData;
  }

  private String fullName;
  private List<Worker_Service> servicesData;

}
