package com.example.demo.Controller.WorkerDasboardController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Model.Booking;
import com.example.demo.Model.ConfirmServices;
import com.example.demo.Model.SignUpLoginModel;
import com.example.demo.Model.Worker_Service;
import com.example.demo.Repository.BookedRepo;
import com.example.demo.Repository.ConfirmRepo;
import com.mongodb.client.result.UpdateResult;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkerDashboard {

  @Autowired
  private BookedRepo FetchBookedService;

  @Autowired
  private MongoOperations mongoOperations;

  @Autowired
  private ConfirmRepo confirmRepo;

  @GetMapping("/WorkerProfile")
  public String WorkerProfile(@CookieValue(value = "encryptedEmail", defaultValue = "") String email, Model model,
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
    System.out.println(firstName);
    model.addAttribute("firstName", firstName);

    System.out.println(username);
    model.addAttribute("data", data);
    return "WorkerDashboard/MyProfile";
  }

  @GetMapping("/WorkerHistory")
  public String WorkerHistory(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId,
      ConfirmServices confirmServices, Model model) {
    Query hQuery = new Query(Criteria.where("WorkerEmailId").is(EmailId));
    List<ConfirmServices> historyList = mongoOperations.find(hQuery, ConfirmServices.class);
    System.out.println("Worker History : " + historyList.get(0).getMoney_Charge());
    model.addAttribute("history", historyList);
    return "WorkerDashboard/History";
  }

  @GetMapping("/WorkerHired")
  public String WorkerHired(@CookieValue(value = "encryptedEmail", defaultValue = "") String EmailId,
      Model model, Booking booked) {
    Query query = new Query(Criteria.where("workerEmailId").is(EmailId));
    List<Booking> hireList = mongoOperations.find(query, Booking.class);
    model.addAttribute("hireList", hireList);
    return "WorkerDashboard/Hired";
  }

  @GetMapping("/ConfirmRequestService")
  public String ConfirmRequestService(@RequestParam("UserEmail") String userEmail,
      @RequestParam("UserService") String UserService, Model model) {
    Query checkQuery = new Query(Criteria.where("userEmailId").is(userEmail).where("servicetypes").is(UserService));

    Update updateDefine = new Update().set("Status", "Accept");
    UpdateResult res = mongoOperations.updateFirst(checkQuery, updateDefine, Booking.class);

    Query cs = new Query(Criteria.where("userEmailId").is(userEmail).where("servicetypes").is(UserService));
    List<Booking> checkstatus = mongoOperations.find(cs, Booking.class);

    // Check if there's already a confirmation record
    Query confirmQuery = new Query(Criteria.where("userEmailId").is(userEmail).and("service_Name").is(UserService));
    List<ConfirmServices> existingConfirm = mongoOperations.find(confirmQuery, ConfirmServices.class);

    if (!checkstatus.isEmpty() && checkstatus.get(0).getStatus().equals("Accept")) {
      ConfirmServices confirm = new ConfirmServices();
      confirm.setWorkerName(checkstatus.get(0).getWorkerName());
      confirm.setService_Name(checkstatus.get(0).getServicetypes());
      confirm.setSetWorkerEmailId(checkstatus.get(0).getWorkerEmailId());
      confirm.setDay_of_Booking(checkstatus.get(0).getDayofBooking());
      confirm.setMoney_Charge(checkstatus.get(0).getMoney_Charge());
      confirm.setUserName(checkstatus.get(0).getUserName());
      confirm.setUserEmailId(checkstatus.get(0).getUserEmailId());
      confirm.setUserContact(checkstatus.get(0).getWorkerContact());
      this.confirmRepo.save(confirm);
    }

    return "redirect:/WorkerHired";
  }

  @GetMapping("/DeclineRequestService")
  public String DeclineRequestService(@RequestParam("UserEmail") String userEmail,
      @RequestParam("UserService") String UserService) {
    Query checkQuery = new Query(Criteria.where("userEmailId").is(userEmail).where("servicetypes").is(UserService));

    Update updateDefine = new Update().set("Status", "Decline");
    UpdateResult res = mongoOperations.updateFirst(checkQuery, updateDefine, Booking.class);
    // Check status
    Query rmq = new Query(Criteria.where("userEmailId").is(userEmail).where("servicetypes").is(UserService));
    List<Booking> checkstatus = mongoOperations.find(rmq, Booking.class);
    System.out.println(checkstatus.get(0).getStatus());

    if (!checkstatus.isEmpty() && checkstatus.get(0).getStatus().equals("Decline")) {
      Query rmq2 = new Query(Criteria.where("userEmailId").is(userEmail).where("Service_Name").is(UserService));
      mongoOperations.findAndRemove(rmq2, ConfirmServices.class);
    }

    return "redirect:/WorkerHired";
  }

  @GetMapping("/WorkerPayment")
  public String WorkerPayment() {
    return "WorkerDashboard/Payment";
  }

  @GetMapping("/WorkerService")
  public String WorkerService(@CookieValue(value = "encryptedEmail", defaultValue = "") String emailId,
      Model model) {
    Query query = new Query(Criteria.where("EmailId").is(emailId));
    List<Worker_Service> work = mongoOperations.find(query, Worker_Service.class);
    model.addAttribute("Work", work);

    return "WorkerDashboard/My_Service";
  }

  @PostMapping("/publish")
  public String publish() {
    System.out.println("Your Service Upload Sccessfully Worker!");
    return "redirect:/WorkerService";
  }

  @GetMapping("/workerinvoice")
  public String workerinvoice(ConfirmServices confirmServices, Model model,
      @RequestParam("InVoicedetail") String UserEmail,
      @RequestParam("ServiceName") String BookedService) {
    Query iQuery = new Query(Criteria.where("userEmailId").is(UserEmail).where("Service_Name").is(BookedService));
    List<ConfirmServices> iconfirm = mongoOperations.find(iQuery, ConfirmServices.class);
    System.out.println("Worker detail :" + iconfirm.get(0).getHour_Rate());
    model.addAttribute("Winvoice", iconfirm);
    model.addAttribute("Date", new Date());

    // More data from service collection
    Query q = new Query(Criteria.where("EmailId").is(UserEmail).where("Service_Name").is(BookedService));
    List<Worker_Service> wservice = mongoOperations.find(q, Worker_Service.class);
    model.addAttribute("hour", wservice.get(0).getHour());
    model.addAttribute("description", wservice.get(0).getDescription());
    return "WorkerDashboard/Invoice";
  }

}
