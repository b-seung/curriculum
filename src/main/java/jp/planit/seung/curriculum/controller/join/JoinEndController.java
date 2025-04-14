package jp.planit.seung.curriculum.controller.join;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.UrlConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join/end")
@RequiredArgsConstructor
public class JoinEndController {

  private final HttpSession session;
  private final JoinService joinService;

  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(null);

    session.removeAttribute(UrlConst.JOIN);
    session.removeAttribute(UrlConst.JOIN_NEXT);

    return mv;
  }

  @PostMapping("/ok")
  public ResponseEntity<?> ok() {

    return ResponseEntity.ok(new BaseResponse());
  }
}
