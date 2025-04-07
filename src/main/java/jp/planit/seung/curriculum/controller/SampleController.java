package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController extends BaseController {
  @RequestMapping("/index")
  public String index() {
    return "index.html";
  }

  @RequestMapping("/home")
  public String home() {
    return "home.html";
  }
}
