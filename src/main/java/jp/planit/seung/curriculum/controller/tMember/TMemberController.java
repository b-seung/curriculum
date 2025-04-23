package jp.planit.seung.curriculum.controller.tMember;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.planit.seung.curriculum.dto.member.MemberSearchRequest;
import jp.planit.seung.curriculum.dto.member.MemberSearchResponse;
import jp.planit.seung.curriculum.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class TMemberController {

  private final MemberService service;

  @RequestMapping("")
  public ModelAndView index() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/member/member_meisai");

    return mv;
  }

  @PostMapping("/search")
  public ResponseEntity<?> search(@RequestBody MemberSearchRequest request) {
    MemberSearchResponse res = service.search(request);

    res.setHttpStatus(HttpStatus.OK.value());

    return ResponseEntity.ok(res);
  }
}
