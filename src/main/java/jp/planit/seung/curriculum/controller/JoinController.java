package jp.planit.seung.curriculum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/join")
public class JoinController extends BaseController {
  @GetMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView("join_sample");
    // ModelAndView mv = new ModelAndView("join");
    return mv;
  }

}
