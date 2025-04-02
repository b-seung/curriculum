package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController extends BaseController {
  @GetMapping("/")
  public String index() {
    return "index.html";
  }

  @GetMapping("/home")
  public String home() {
    return "home.html";
  }
}
