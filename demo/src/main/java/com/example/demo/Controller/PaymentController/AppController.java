package com.example.demo.Controller.PaymentController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.PaymentModel.Request;

@Controller
public class AppController {
  @Value("${stripe.api.publicKey}")
  private String publicKey;

  @GetMapping("/payment")
  public String home(Model model) {
    model.addAttribute("request", new Request());
    return "Payment/index";
  }

  @PostMapping("/checkoutPage")
  public String showCard(@ModelAttribute @Valid Request request,
      BindingResult bindingResult,
      Model model, @RequestParam("UserEmail") String UserEmail, @RequestParam("Money_charge") String Money_charge) {
    model.addAttribute("publicKey", publicKey);
    model.addAttribute("amount", Money_charge);
    model.addAttribute("email", UserEmail);
    return "Payment/checkout";
  }
}
