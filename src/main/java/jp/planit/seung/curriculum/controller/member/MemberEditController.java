package jp.planit.seung.curriculum.controller.member;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.constants.SessionConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.dto.member.MemberEditRequest;
import jp.planit.seung.curriculum.exception.CustomException;
import jp.planit.seung.curriculum.service.MemberService;
import jp.planit.seung.curriculum.validation.member.MemberEditValidator;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member/edit")
@RequiredArgsConstructor
public class MemberEditController {

  private final HttpSession session;
  private final MemberEditValidator memberEditValidator;
  private final MemberService service;

  @InitBinder("memberEditRequest")
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(memberEditValidator);
  }

  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.MEMBER_FOLDER + ScreenIdConst.MEMBER_EDIT);

    Map<String, Object> member = (Map<String, Object>) session.getAttribute(ScreenIdConst.MEMBER);
    String mode = (String) session.getAttribute(ScreenIdConst.MEMBER + "Mode");

    mv.addObject("member", member);
    mv.addObject("mode", mode);

    return mv;
  }

  @PostMapping("/back")
  public ResponseEntity<?> back() {
    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/member");

    return ResponseEntity.ok(res);
  }

  @PostMapping("/insert")
  public ResponseEntity<?> insert(@Valid @RequestBody MemberEditRequest request, Errors errors) {
    if (errors.hasErrors()) {
      throw new CustomException(service.getValidErrorMsg(errors));
    }

    service.insert(request);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/member");

    return ResponseEntity.ok(res);
  }

  @PutMapping("/update")
  public ResponseEntity<?> update(@Valid @RequestBody MemberEditRequest request, Errors errors) {
    if (errors.hasErrors()) {
      throw new CustomException(service.getValidErrorMsg(errors));
    }

    service.update(request);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/member");

    return ResponseEntity.ok(res);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") String id, @Valid @RequestBody MemberEditRequest request,
      Principal principal) throws Exception {
    String userId = principal.getName();

    service.delete(id, request.getMode());

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/member");

    return ResponseEntity.ok(res);
  }
}
