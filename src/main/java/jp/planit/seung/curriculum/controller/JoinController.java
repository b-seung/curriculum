package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController extends BaseController {
  @GetMapping("")
  public String index() {
    return "join_sample.html";
    // return "join.html";
  }

}
