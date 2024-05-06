package com.example.demo.Controller.UserDashboardController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.demo.Model.Booking;
import com.example.demo.Model.ConfirmServices;
import com.example.demo.Model.SignUpLoginModel;
import com.example.demo.Model.UserServiceData;
import com.example.demo.Model.Worker_Service;
import com.example.demo.Repository.BookedRepo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainDashboardController {
  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private BookedRepo bookedRepo;

  @GetMapping("/Book-Now")
  public String Book_Now(@RequestParam("id") String emailId, @RequestParam("service") String service, Model model,
      @CookieValue(value = "encryptedEmail", defaultValue = "") String userEmail) {
    // worker Data
    Query query = new Query(Criteria.where("EmailId").is(emailId));
    SignUpLoginModel workerData = mongoOperations.findOne(query, SignUpLoginModel.class);
    // service Data
    Query fetchserviceQuery = new Query(Criteria.where("EmailId").is(emailId).and("Service_Name").is(service));
    Worker_Service workerServiceDetail = mongoOperations.findOne(fetchserviceQuery, Worker_Service.class);
    // userData
    SignUpLoginModel userData = mongoOperations.findOne(new Query(Criteria.where("EmailId").is(userEmail)),
        SignUpLoginModel.class);

    List<String> weekdays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
        "Monday TO Frieday", "Any Weekend Days");
    model.addAttribute("weekdays", weekdays);

    model.addAttribute("worker", workerData);
    model.addAttribute("user", userData);
    model.addAttribute("workerserviced", workerServiceDetail);
    return "UserDashboard/ConfirmService";
  }

  @GetMapping("/confirmhalf")
  public String confirmhalf() {
    return "UserDashboard/ConfirmService";
  }

  @PostMapping("/confirm_Submit")
  public String confirmSubmit(@RequestParam("WorkerName") String Workername,
      @RequestParam("Service_Name") String serviceType, @RequestParam("UserName") String Username,
      @RequestParam("days") String days,
      @CookieValue(value = "encryptedEmail", defaultValue = "") String userEmail) {

    // user details
    Query userdata = new Query(Criteria.where("EmailId").is(userEmail));

    SignUpLoginModel user = mongoOperations.findOne(userdata,
        SignUpLoginModel.class);

    // Worker details
    Query worker = new Query(Criteria.where("Service_Name").is(serviceType));
    Worker_Service workerdata = mongoOperations.findOne(worker,
        Worker_Service.class);

    // Booked Service data

    Booking ServiceBooked = new Booking();
    ServiceBooked.setUserName(user.getFullName());
    ServiceBooked.setWorkerName(Workername);
    ServiceBooked.setWorkerContact(user.getPhone_Number());
    ServiceBooked.setUserEmailId(userEmail);
    ServiceBooked.setWorkerEmailId(workerdata.getEmailId());
    ServiceBooked.setServicetypes(workerdata.getService_Name());
    ServiceBooked.setDayofBooking(days);
    ServiceBooked.setUserAddress(user.getStreet_Address());
    ServiceBooked.setUserCity(user.getCity());
    ServiceBooked.setLandmarks(user.getTown_Area());
    ServiceBooked.setUserPinCode(user.getPinCode());
    ServiceBooked.setMoney_Charge(workerdata.getMoney_charge());
    ServiceBooked.setHour_Rate(workerdata.getHour());
    ServiceBooked.setStatus("Pending");
    this.bookedRepo.save(ServiceBooked);

    System.out.println("Data confirm getting" + Workername + Username + days);
    return "redirect:/services";
  }

  @GetMapping("/userdashboard")
  public String getMethodName(Model model, HttpServletRequest request,
      @CookieValue(value = "encryptedEmail", defaultValue = "") String email) {
    SignUpLoginModel userData = mongoOperations.findOne(new Query(Criteria.where("EmailId").is(email)),
        SignUpLoginModel.class);
    model.addAttribute("option", userData.getType());
    return "UserDashboard/MainDashboard";
  }

  @GetMapping("/myprofile")
  public String decrypt(@CookieValue(value = "encryptedEmail", defaultValue = "") String email, Model model,
      HttpSession session) {
    Query query = new Query(Criteria.where("EmailId").is(email));
    SignUpLoginModel data = mongoOperations.findOne(query, SignUpLoginModel.class);
    String username = (String) session.getAttribute("option");
    String fullName = data.getFullName();
    String[] nameParts = fullName.split(" ");
    String firstName = "";
    if (nameParts.length > 0) {
      firstName = nameParts[0];
    }
    System.out.println(data.getGender());
    model.addAttribute("firstName", firstName);
    model.addAttribute("data", data);
    return "UserDashboard/MyProfile";
  }

  @GetMapping("/services")
  public String Services(Model model) {
    List<UserServiceData> allUserData = new ArrayList<>();
    Query query = new Query(Criteria.where("types").is("worker"));
    List<SignUpLoginModel> data = mongoOperations.find(query, SignUpLoginModel.class);
    for (SignUpLoginModel signUpLoginModel : data) {

      Query queryforSevices = new Query(Criteria.where("EmailId").is(signUpLoginModel.getEmailId()));
      List<Worker_Service> servicesData = mongoOperations.find(queryforSevices, Worker_Service.class);
      UserServiceData userServiceData = new UserServiceData();
      userServiceData.setFullName(signUpLoginModel.getFullName());
      userServiceData.setServicesData(servicesData);
      allUserData.add(userServiceData);

    }
    model.addAttribute("allUserData", allUserData);
    return "UserDashboard/AllService";

  }

  @GetMapping("/booking")
  public String booked(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId,
      Model model, Booking booked) {

    Query query = new Query(Criteria.where("userEmailId").is(EmailId));
    List<Booking> BookingList = mongoOperations.find(query, Booking.class);

    int num = BookingList.size();
    model.addAttribute("BookingList", BookingList);
    model.addAttribute("Bookingnumberlist", num);
    //

    return "UserDashboard/Booking";
  }

  @GetMapping("/history")
  public String history(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId, Model model,
      ConfirmServices confirmServices) {
    Query qhistory = new Query(Criteria.where("userEmailId").is(EmailId));
    List<ConfirmServices> historyList = mongoOperations.find(qhistory, ConfirmServices.class);
    System.out.println("History : " + historyList.get(0).getMoney_Charge());
    model.addAttribute("history", historyList);
    return "UserDashboard/History";
  }

  @GetMapping("/confirm")
  public String confirmed(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId, Model model,
      Booking booking) {
    Query csq = new Query(Criteria.where("userEmailId").is(EmailId));
    List<ConfirmServices> cs = mongoOperations.find(csq, ConfirmServices.class);
    System.out.println(" Money charge: " + cs.get(0).getMoney_Charge());
    System.out.println("Worker Email: " + cs.get(0).getSetWorkerEmailId());
    System.out.println("User Email: " + cs.get(0).getUserEmailId());
    model.addAttribute("confirm", cs);
    return "UserDashboard/Confirm";
  }

  // Profile Update and Save
  @PostMapping("/Update")
  public String Update(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId,
      @RequestParam("fname") String fname, @RequestParam("email") String email, @RequestParam("phone") String Contact,
      @RequestParam("password") String Password,
      @RequestParam("gender") String Gender, @RequestParam("address") String Address,
      @RequestParam("Town") String Town_Area, @RequestParam("district") String District,
      @RequestParam("City") String City_Name, @RequestParam("State") String State,
      @RequestParam("pincode") String PinCode) {
    System.out.println("Update" + fname);
    Query query = new Query(Criteria.where("EmailId").is(EmailId));
    Update update = new Update();

    return "redirect:/myprofile";
  }

  @GetMapping("/userinvoice")
  public String getMethodName(ConfirmServices confirmServices, Model model,
      @RequestParam("InVoicedetail") String WorkerEmail,
      @RequestParam("ServiceName") String BookedService) {
    Query q = new Query(Criteria.where("WorkerEmailId").is(WorkerEmail).where("Service_Name").is(BookedService));
    List<ConfirmServices> Invoices = mongoOperations.find(q, ConfirmServices.class);
    System.out.println("Invoice :" + Invoices.get(0).getWorkerName());
    model.addAttribute("worker", Invoices);
    return "UserDashboard/Invoice";
  }

}
