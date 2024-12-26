package com.busanit501.travelproject.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminJh1Controller {

  @GetMapping("/admin/product/register")
  public String registerProduct() {
    return "admin/registerProduct_jh1";
  }

  @GetMapping("/admin/product/list")
  public String listProduct() {
    return "admin/listProduct_jh1";
  }
}
