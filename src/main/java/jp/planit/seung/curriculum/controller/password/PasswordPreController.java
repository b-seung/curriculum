package jp.planit.seung.curriculum.controller.password;

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

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.dto.password.PasswordPreRequest;
import jp.planit.seung.curriculum.exception.CustomException;
import jp.planit.seung.curriculum.service.PasswordService;
import jp.planit.seung.curriculum.validation.password.PasswordPreValidator;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/password/pre")
@RequiredArgsConstructor
public class PasswordPreController {

  private final HttpSession session;
  private final PasswordService service;
  private final PasswordPreValidator passwordPrreValidator;

  @InitBinder("passwordPreRequest")
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(passwordPrreValidator);
  }

  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.PASSWORD_PRE_FOLDER + ScreenIdConst.PASSWORD_PRE);

    return mv;
  }

  @PostMapping("/ok")
  public ResponseEntity<?> ok(@Valid @RequestBody PasswordPreRequest request, Errors errors) throws JSONException {
    if (errors.hasErrors()) {
      throw new CustomException(service.getValidErrorMsg(errors));
    }

    String idOrToken = service.getTokenOrInfo(request);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());

    session.setAttribute(ScreenIdConst.PASSWORD_PRE, idOrToken);

    if (request.getSearchKbn().equals("0")) {
      res.setUrl("/password/pre/search");
    } else {
      res.setUrl("/password/pre/send");
    }

    return ResponseEntity.ok(res);
  }

  @RequestMapping("/search")
  public ModelAndView search() throws Exception {
    String id = (String) session.getAttribute(ScreenIdConst.PASSWORD_PRE);
    session.removeAttribute(ScreenIdConst.PASSWORD_PRE);

    if (StringUtils.isEmpty(id)) {
      throw new Exception("");
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.PASSWORD_PRE_FOLDER + ScreenIdConst.PASSWORD_PRE_ID);
    mv.addObject("id", id);

    return mv;
  }

  @RequestMapping("/send")
  public ModelAndView send() throws Exception {
    String token = (String) session.getAttribute(ScreenIdConst.PASSWORD_PRE);
    session.removeAttribute(ScreenIdConst.PASSWORD_PRE);

    if (StringUtils.isEmpty(token)) {
      throw new Exception("");
    }

    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.PASSWORD_PRE_FOLDER + ScreenIdConst.PASSWORD_PRE_PW);
    mv.addObject("token", token);

    return mv;
  }

  @PostMapping("/send/ok")
  public ResponseEntity<?> sendOk() {

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/login");

    return ResponseEntity.ok(res);
  }
}
