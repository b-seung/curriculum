package jp.planit.seung.curriculum.controller.join;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.UrlConst;
import jp.planit.seung.curriculum.dto.JoinRequest;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join/next")
@RequiredArgsConstructor
public class JoinNextController {

  private final HttpSession session;
  private final JoinService joinService;

  @GetMapping("")
  public ModelAndView joinNextIndex(Model model) throws Exception {
    JoinRequest joinRequestDto = (JoinRequest) session.getAttribute(UrlConst.JOIN);
    session.setAttribute(UrlConst.JOIN_NEXT, joinRequestDto);

    ModelAndView mv = new ModelAndView();
    mv.setViewName(UrlConst.JOIN_NEXT);

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

  @PostMapping("/ok")
  public ResponseEntity<?> joinNextOk(@RequestBody JoinRequest request) {

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/join/end");

    return ResponseEntity.ok().body(res);
  }

  @PostMapping("/back")
  public ResponseEntity<?> joinNextBack() {
    session.removeAttribute(UrlConst.JOIN_NEXT);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    return ResponseEntity.ok(res);
  }
}
