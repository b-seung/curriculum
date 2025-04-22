package jp.planit.seung.curriculum.controller.join;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join/end")
@RequiredArgsConstructor
public class JoinEndController {

  private final HttpSession session;

  @RequestMapping("")
  public ModelAndView jojinEndIndex() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.JOIN_FOLDER + ScreenIdConst.JOIN_END);

    session.removeAttribute(ScreenIdConst.JOIN);
    session.removeAttribute(ScreenIdConst.JOIN_NEXT);

    return mv;
  }

  @PostMapping("/ok")
  public ResponseEntity<?> joinEndOk() {

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/login");

    return ResponseEntity.ok(res);
  }
}
