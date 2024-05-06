package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Model.SignUpLoginModel;
import com.example.demo.Repository.SignUpLoginRepo;
import com.mongodb.DuplicateKeyException;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class signuploginregistrations {

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/registrations")
  public String registeration(
      @ModelAttribute("type") String userType, SignUpLoginModel signUpLoginModel,
      RedirectAttributes redirectAttributes, Model model) {
    model.addAttribute("userType", userType);
    return "signuploginregistration/registration";
  }

  @Autowired
  private SignUpLoginRepo signUpLoginRepo;

  // Sendind data and Validation
  @PostMapping("/submitregistration")
  public String Submit(@Valid @ModelAttribute("signUpLoginModel") SignUpLoginModel signUpLoginModel,
      BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response,
      HttpServletRequest request,
      @RequestParam("type") String userType)
      throws Exception {
    if (bindingResult.hasErrors()) {
      System.out.println("error occured");
      return "signuploginregistration/registration";

    }
    // success code here
    try {
      if (signUpLoginRepo.findByEmailId(signUpLoginModel.getEmailId()) != null) {
        bindingResult.rejectValue("EmailId", "error.signUpLoginModel", "Email already exists");
        return "signuploginregistration/registration";
      }
      if (userType.equals("user")) {
        Cookie emailCookie = new Cookie("encryptedEmail", signUpLoginModel.getEmailId());
        response.addCookie(emailCookie);
        HttpSession session = request.getSession();
        session.setAttribute("option", signUpLoginModel.getType());

        this.signUpLoginRepo.save(signUpLoginModel);
        // model.addAttribute("option", userType);
        return "redirect:/myprofile";

      } else if (userType.equals("worker")) {
        Cookie emailCookie = new Cookie("encryptedEmail", signUpLoginModel.getEmailId());
        response.addCookie(emailCookie);
        signUpLoginModel.setType(userType);
        HttpSession session = request.getSession();
        session.setAttribute("option", signUpLoginModel.getType());

        this.signUpLoginRepo.save(signUpLoginModel);
        // model.addAttribute("option", userType);
        return "redirect:/WorkerProfile";
      }

      return "redirect:/registrations";
    } catch (DuplicateKeyException e) {
      bindingResult.rejectValue("EmailId", "error.signUpLoginModel", "Email already exists");

      return "signuploginregistration/registration";
    } catch (Exception e) {
      return "ErrorTemplate/oppsPage";
    }
  }

  @GetMapping("/login")
  public String login(Model model, SignUpLoginModel signUpLoginModel) {
    model.addAttribute("Check", signUpLoginModel);
    return "signuploginregistration/login";
  }

  @PostMapping("/logindone")
  public String logindone(@Valid @ModelAttribute("Check") SignUpLoginModel signUpLoginModel,
      BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, HttpServletResponse response,
      HttpServletRequest request) throws InterruptedException {

    HttpSession session = request.getSession();

    Query query = new Query(Criteria.where("EmailId").is(signUpLoginModel.getEmailId()).and("Password").is(
        signUpLoginModel.getPassword()));
    SignUpLoginModel data = mongoOperations.findOne(query,
        SignUpLoginModel.class);

    if (data == null) {
      if (bindingResult.hasErrors()) {
        return "signuploginregistration/login";
      }
      return "redirect:/login";
    }
    String check = (String) data.getType();
    if (check == data.getType()) {
      System.out.println(data.getType());

      session.setAttribute("option", data.getType());
      Cookie emailCookie = new Cookie("encryptedEmail", data.getEmailId());
      response.addCookie(emailCookie);
      emailCookie.setMaxAge(60);

      return "redirect:/myprofile";

    } else if (check == data.getType()) {
      session.setAttribute("option", data.getType());
      Cookie emailCookie = new Cookie("encryptedEmail", data.getEmailId());
      response.addCookie(emailCookie);
      emailCookie.setMaxAge(60);
      return "redirect:/WorkerProfile";
    }
    // session.setAttribute("option", data.getType());
    // Cookie emailCookie = new Cookie("encryptedEmail", data.getEmailId());
    // response.addCookie(emailCookie);

    // Redirect to the profile page if the email exists
    System.out.println("Login problem occure");
    return "redirect:/login";
  }

}
