package jp.planit.seung.curriculum.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.dto.JoinRequestDto;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JoinController extends BaseController {

  private final HttpSession session;
  private final JoinService joinService;

  @GetMapping("/join")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("join_sample");

    return mv;
  }

  @PostMapping("/join/next")
  public ResponseEntity<?> next(@RequestBody JoinRequestDto request) {
    session.setAttribute("join_check_sample", request);

    Map<String, Object> res = new HashMap<>();
    res.put("url", "/joinCheck");
    return ResponseEntity.ok().body(res);
  }

  @GetMapping("/joinCheck")
  public ModelAndView next2(Model model) {
    JoinRequestDto joinRequestDto = (JoinRequestDto) session.getAttribute("join_check_sample");
    session.removeAttribute("join_check_sample");

    ModelAndView mv = new ModelAndView();
    mv.setViewName("join_check_sample");

    Map<String, Object> params = new HashMap<>();
    params.put("id", joinRequestDto.getId());
    params.put("name", joinRequestDto.getName());
    params.put("birthday", joinRequestDto.getBirthday());
    params.put("gender", joinRequestDto.getSeibetsu());
    params.put("genderName", joinService.getSeibetsu(joinRequestDto.getSeibetsu()));
    params.put("postcode", joinRequestDto.getPostcode());
    params.put("phoneNo", joinRequestDto.getPhoneNo());
    mv.addAllObjects(params);

    return mv;
  }

}
