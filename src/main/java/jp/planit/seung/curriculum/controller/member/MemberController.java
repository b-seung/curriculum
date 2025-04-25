package jp.planit.seung.curriculum.controller.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import groovyjarjarantlr4.v4.parse.ANTLRParser.id_return;
import jakarta.servlet.http.HttpSession;
import jp.planit.seung.curriculum.constants.ScreenIdConst;
import jp.planit.seung.curriculum.dto.base.BaseResponse;
import jp.planit.seung.curriculum.dto.member.MemberNextRequest;
import jp.planit.seung.curriculum.dto.member.MemberSearchRequest;
import jp.planit.seung.curriculum.dto.member.MemberSearchResponse;
import jp.planit.seung.curriculum.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

  private final MemberService service;
  private final HttpSession session;

  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName(ScreenIdConst.MEMBER_FOLDER + ScreenIdConst.MEMBER);

    session.removeAttribute(ScreenIdConst.MEMBER);
    session.removeAttribute(ScreenIdConst.MEMBER + "Mode");

    return mv;
  }

  @PostMapping("/search")
  public ResponseEntity<?> search(@RequestBody MemberSearchRequest request) {
    MemberSearchResponse res = service.search(request);

    res.setHttpStatus(HttpStatus.OK.value());

    return ResponseEntity.ok(res);
  }

  @PostMapping("/{mode}")
  public ResponseEntity<?> edit(@PathVariable("mode") String mode, @RequestBody MemberNextRequest request) {
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Object> param = objectMapper.convertValue(request, Map.class);
    session.setAttribute(ScreenIdConst.MEMBER, param);
    session.setAttribute(ScreenIdConst.MEMBER + "Mode", mode);

    BaseResponse res = new BaseResponse();
    res.setHttpStatus(HttpStatus.OK.value());
    res.setUrl("/member/edit");

    return ResponseEntity.ok(res);
  }
}
