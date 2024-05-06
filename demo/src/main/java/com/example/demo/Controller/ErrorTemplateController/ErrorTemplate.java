package com.example.demo.Controller.ErrorTemplateController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorTemplate {

  @GetMapping("/")
  public String ErrorPage() {
    return "ErrorTemplate/oppsPage";
  }

}
