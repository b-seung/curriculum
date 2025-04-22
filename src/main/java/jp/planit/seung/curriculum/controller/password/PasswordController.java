package jp.planit.seung.curriculum.controller.password;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.dto.password.PasswordRequest;
import jp.planit.seung.curriculum.entity.TokenEntity;
import jp.planit.seung.curriculum.service.PasswordService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {

  private final HttpSession session;
  private final PasswordService service;

  @GetMapping("/{token}")
  public ModelAndView joinIndex(@PathVariable("token") String token) {

    ModelAndView mv = new ModelAndView();

    TokenEntity tokenInfo = service.getToken(token);

    if (tokenInfo == null) {
      mv.setViewName(ScreenIdConst.TOKEN_ERROR);
      session.removeAttribute("token");

      return mv;
    }

    mv.setViewName(ScreenIdConst.PASSWORD_FOLDER + ScreenIdConst.PASSWORD);

    session.setAttribute("token", tokenInfo);

    return mv;
  }

  @PostMapping("/ok")
  public ResponseEntity<?> ok(@Valid @RequestBody PasswordRequest request, Errors errors) throws Exception {
    TokenEntity tokenInfo = (TokenEntity) session.getAttribute("token");

    service.resetPassword(request, tokenInfo);

    session.removeAttribute("token");

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/password/end");

    return ResponseEntity.ok(res);
  }

  @PostMapping("/back")
  public ResponseEntity<?> back() {
    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/login");

    return ResponseEntity.ok(res);
  }

  @GetMapping("/end")
  public ModelAndView end() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.PASSWORD_FOLDER + ScreenIdConst.PASSWORD_END);

    return mv;
  }

  @PostMapping("/end/ok")
  public ResponseEntity<?> endOk() {
    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/login");

    return ResponseEntity.ok(res);
  }

}
