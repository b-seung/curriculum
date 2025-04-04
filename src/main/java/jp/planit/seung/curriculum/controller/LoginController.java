package jp.planit.seung.curriculum.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.xml.ws.Response;
import jp.planit.seung.curriculum.entity.Member;

@Controller
public class LoginController extends BaseController {
  @RequestMapping("/login")
  public String index() {
    return "login.html";
  }

  // @GetMapping("/login")
  // public String login(LoginRequest request) {
  // return "redirect:/member/regist";
  // }
}
