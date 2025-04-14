package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.planit.seung.curriculum.constants.UrlConst;

@Controller
public class LoginController {

  @RequestMapping("/login")
  public String index() {
    return UrlConst.LOGIN;
  }
}
