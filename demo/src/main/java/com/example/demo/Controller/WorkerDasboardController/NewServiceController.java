package com.example.demo.Controller.WorkerDasboardController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Model.SignUpLoginModel;
import com.example.demo.Model.Worker_Service;
import com.example.demo.Repository.Services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class NewServiceController {

  @Autowired
  private Services services;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/WorkerNewService")
  public String WorkerNewService(Model model) {
    model.addAttribute("worker_Service", new Worker_Service());
    return "WorkerDashboard/Create_New_Service";
  }

  @PostMapping("/upload")
  public String upload(@Valid @ModelAttribute("worker_Service") Worker_Service worker_Service,
      BindingResult bindingResult, HttpServletResponse response, @CookieValue("encryptedEmail") String emailId,
      SignUpLoginModel signUpLoginModel) {

    if (bindingResult.hasErrors()) {
      return "WorkerDashboard/Create_New_Service";
    }

    if (services.findByService_Name(worker_Service.getService_Name()) != null) {
      bindingResult.rejectValue("Work_Type", "error.worker_Service", "Same Service Already exist");
      return "WorkerDashboard/Create_New_Service";
    }
    Query wcq = new Query(Criteria.where("EmailId").is(emailId).where("types").is("worker"));
    List<SignUpLoginModel> wc = mongoOperations.find(wcq, SignUpLoginModel.class);
    worker_Service.setEmailId(emailId);
    worker_Service.setWorkerContact(wc.get(0).getPhone_Number());
    this.services.save(worker_Service);
    return "redirect:/WorkerService";
  }
}
