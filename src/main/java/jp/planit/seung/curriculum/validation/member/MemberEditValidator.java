package jp.planit.seung.curriculum.validation.member;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.planit.seung.curriculum.dto.member.MemberEditRequest;
import jp.planit.seung.curriculum.service.MemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberEditValidator implements Validator {

  private final MemberService memberService;

  @Override
  public boolean supports(Class<?> clazz) {
    return MemberEditRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    MemberEditRequest request = (MemberEditRequest) target;

    String mode = request.getMode();

    if (mode.equals("add") || mode.equals("copy") || mode.equals("update")) {
      if (!StringUtils.isEmpty(request.getBirthday())) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(request.getBirthday());
        if (memberService.isAfterDate(today, birthday)) {
          errors.rejectValue("birthday", null, "生年月日は本日よりも前の日付にしてください。");
        }
      }

      if (mode.equals("add") || mode.equals("copy")) {
        if (StringUtils.isEmpty(request.getPassword())) {
          errors.rejectValue("password", null, "新規・複写の場合、パスワードは必須項目です。");
        }

        if (memberService.checkId(request.getId())) {
          errors.rejectValue("id", null, "入力されたIDは既に使用されています。");
        }

        if (memberService.checkEmail(request.getEmail())) {
          errors.rejectValue("email", null, "入力されたメールアドレス既に使用されています。");
        }

      }
    }

  }

}
