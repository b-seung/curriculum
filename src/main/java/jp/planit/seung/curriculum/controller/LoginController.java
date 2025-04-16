package jp.planit.seung.curriculum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.ScreenIdConst;

@Controller
public class LoginController {
  @Autowired
  private HttpSession session;

  @RequestMapping("/login")
  public String index() {
    session.removeAttribute("token");
    return ScreenIdConst.LOGIN;
  }
}
