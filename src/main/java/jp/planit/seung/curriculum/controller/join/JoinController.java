package jp.planit.seung.curriculum.controller.join;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.planit.seung.curriculum.constants.UrlConst;
import jp.planit.seung.curriculum.dto.JoinIdCheckRequest;
import jp.planit.seung.curriculum.dto.JoinRequest;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.exception.CustomException;
import jp.planit.seung.curriculum.service.JoinService;
import jp.planit.seung.curriculum.validation.join.JoinValidator;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/join")
@RequiredArgsConstructor
public class JoinController {

  private final HttpSession session;
  private final JoinService joinService;
  private final JoinValidator joinValidator;

  @InitBinder("joinRequest")
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(joinValidator);
  }

  @GetMapping("/{token}")
  public ModelAndView joinIndex(@PathVariable("token") String token) {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(UrlConst.JOIN);

    JoinRequest joinRequestDto = (JoinRequest) session.getAttribute(UrlConst.JOIN);

    if (joinRequestDto == null) {
      return mv;
    }

    Map<String, Object> params = new HashMap<>();
    params.put("id", joinRequestDto.getId());
    params.put("name", joinRequestDto.getName());
    params.put("birthday", joinRequestDto.getBirthday());
    params.put("gender", joinRequestDto.getSeibetsu());
    params.put("postcode", joinRequestDto.getPostcode());
    params.put("phoneNo", joinRequestDto.getPhoneNo());
    mv.addAllObjects(params);

    return mv;
  }

  @PostMapping("/idCheck")
  public ResponseEntity<?> joinIdCheck(@RequestBody JoinIdCheckRequest request) {

    BaseResponse res = joinService.checkId(request.getId());

    return ResponseEntity.ok(res);
  }

  @PostMapping("/ok")
  public ResponseEntity<?> joinOk(@Valid @RequestBody JoinRequest request, Errors errors) {
    if (errors.hasErrors()) {
      throw new CustomException(joinService.getValidErrorMsg(errors));
    }

    session.setAttribute(UrlConst.JOIN, request);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/join/next");

    return ResponseEntity.ok(res);
  }

  @PostMapping("/back")
  public ResponseEntity<?> back() {

    session.removeAttribute(UrlConst.JOIN);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    return ResponseEntity.ok(res);
  }

}
