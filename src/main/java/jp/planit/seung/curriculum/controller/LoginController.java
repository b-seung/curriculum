package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {

  @GetMapping("/login")
  public String index() {
    return "login.html";
  }

  // @GetMapping("/login")
  // public String login(LoginRequest request) {
  // return "redirect:/member/regist";
  // }
}
