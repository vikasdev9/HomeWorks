package com.example.demo.Controller.AdminController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Model.Adminlogin;
import com.example.demo.Model.Booking;
import com.example.demo.Model.SignUpLoginModel;
import com.example.demo.Repository.AdminRepo;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/Admin")
  public String Admin(Model model) {
    // User Details
    Query usercount = new Query(Criteria.where("types").is("user"));
    List<SignUpLoginModel> users = mongoOperations.find(usercount, SignUpLoginModel.class);
    // Worker Details
    Query workercount = new Query(Criteria.where("types").is("worker"));
    List<SignUpLoginModel> workers = mongoOperations.find(workercount, SignUpLoginModel.class);
    model.addAttribute("numofuser", users.size());
    model.addAttribute("numofworker", workers.size());

    return "AdminDashBoard/DashBoard";
  }

  @GetMapping("/Adminlogin")
  public String Adminlogin(Adminlogin Admin, Model model) {
    model.addAttribute("Adminlogin", Admin);
    return "AdminDashBoard/login";
  }

  @PostMapping("/Adminloginsubmit")
  public String postMethodName(@Valid @ModelAttribute("Adminlogin") Adminlogin adminlogin, BindingResult bindingResult,
      Model model, @RequestParam("AdminEmail") String AdminEmail,
      @RequestParam("Adminpassword") String Adminpassword) {
    if (bindingResult.hasErrors()) {
      return "AdminDashBoard/login";
    }
    Query Eadmin = new Query(Criteria.where("AdminEmail").is(AdminEmail));
    Adminlogin checklogin = mongoOperations.findOne(Eadmin, Adminlogin.class);
    if (checklogin == null) {
      return "AdminDashBoard/login";
    }
    if (checklogin.getAdminEmail().equals(adminlogin.getAdminEmail())) {
      return "redirect:/Admin";
    }

    return "redirect:/Adminlogin";
  }

  @GetMapping("/Admininvoice")
  public String Admininvoice(@RequestParam("user") String numofuser, @RequestParam("worker") String numofworker,
      Model model) {
    System.out.println(numofuser);
    model.addAttribute("unum", numofuser);
    model.addAttribute("wnum", numofworker);
    return "AdminDashBoard/Invoice";
  }

}
