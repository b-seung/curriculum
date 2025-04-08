package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController extends BaseController {

  @RequestMapping("/login")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView("login");
    return mv;
  }

  // @GetMapping("/login")
  // public String login(LoginRequest request) {
  // return "redirect:/member/regist";
  // }
}
