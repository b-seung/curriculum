package jp.planit.seung.curriculum.controller.join;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.dto.join.JoinPreRequest;
import jp.planit.seung.curriculum.exception.CustomException;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join/pre")
@RequiredArgsConstructor
public class JoinPreController {

  private final HttpSession session;
  private final JoinService joinService;

  @GetMapping("")
  public ModelAndView joinPreIndex() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.JOIN_PRE_FOLDER + ScreenIdConst.JOIN_PRE);

    return mv;
  }

  @PostMapping("/ok")
  public ResponseEntity<?> joinPreOk(@Valid @RequestBody JoinPreRequest request, Errors errors) throws JSONException {
    if (errors.hasErrors()) {
      throw new CustomException(joinService.getValidErrorMsg(errors));
    }

    String token = joinService.getToken(request);

    session.setAttribute("token", token);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/join/pre/end");

    return ResponseEntity.ok(res);
  }

  @GetMapping("/end")
  public ModelAndView joinPreEnd() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.JOIN_PRE_FOLDER + ScreenIdConst.JOIN_PRE_END);

    String token = (String) session.getAttribute("token");
    session.removeAttribute("token");
    mv.addObject("token", token);

    return mv;
  }

  @PostMapping("/end/ok")
  public ResponseEntity<?> joinPreEndOk() {
    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/login");

    return ResponseEntity.ok(res);
  }
}
