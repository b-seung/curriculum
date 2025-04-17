package jp.planit.seung.curriculum.validation.join;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.planit.seung.curriculum.dto.join.JoinPreRequest;
import jp.planit.seung.curriculum.dto.join.JoinRequest;
import jp.planit.seung.curriculum.entity.MemberDetailEntity;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JoinPreValidator implements Validator {

  private final JoinService joinService;

  @Override
  public boolean supports(Class<?> clazz) {
    return JoinPreRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    JoinPreRequest request = (JoinPreRequest) target;

    if (joinService.existEmail(request.getEmail())) {
      errors.rejectValue("email", null, "「入力されたメールアドレスのアカウントが既に存在します。");
    }
  }

}
