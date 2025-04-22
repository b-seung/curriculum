package jp.planit.seung.curriculum.controller.tMember;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class TMemberController {
  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/member/member_meisai");

    return mv;
  }
}
