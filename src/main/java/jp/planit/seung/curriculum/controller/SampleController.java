package jp.planit.seung.curriculum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController {

  @RequestMapping("/home")
  public ModelAndView home() {
    ModelAndView mv = new ModelAndView("home");
    return mv;
  }
}
