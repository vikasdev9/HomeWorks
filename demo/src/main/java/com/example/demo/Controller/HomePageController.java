package com.example.demo.Controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Model.ContactUS;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomePageController {

  @GetMapping("/home")
  public String home() {
    return "HomePage/home";
  }

  @PostMapping("/homes")
  public String Home(@RequestParam("userType") String userType, Model model, RedirectAttributes redirectAttributes) {

    if ("user".equals(userType)) {
      // Handle registration for user
      redirectAttributes.addAttribute("type", userType);
      System.out.println(userType);

      return "redirect:/registrations";
    } else if ("worker".equals(userType)) {
      // Handle registration for worker
      System.out.println(userType);
      // redirectAttributes.addAttribute("type", userType);
      redirectAttributes.addFlashAttribute("type", userType);
      return "redirect:/registrations";
    } else {
      return "HomePage/home";

    }

  }

  @GetMapping("/AboutUS")
  public String AboutUS() {
    return "HomePage/AboutUs";
  }

  @GetMapping("/ContactUs")
  public String ContactUs(ContactUS contactUS, Model model) {
    model.addAttribute("AdminContact", contactUS);
    return "HomePage/ContactUs";
  }

  @PostMapping("/contactUsSubmit")
  public String contactUsSubmit(@Valid @ModelAttribute("AdminContact") ContactUS contactUS,
      BindingResult bindingResult) {

    return "";
  }

  @GetMapping("/FAQ")
  public String FAQ() {
    return "HomePage/FAQ";
  }

}
