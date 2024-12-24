package com.busanit501.travelproject.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerJh1Controller {

  @GetMapping("/admin/product/register")
  public String registerProduct() {
    return "registerProduct_jh1";
  }
}
