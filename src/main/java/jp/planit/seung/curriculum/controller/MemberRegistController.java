package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberRegistController extends BaseController {
  @RequestMapping("/regist")
  public String index() {
    return "memberRegist.html";
  }

}
