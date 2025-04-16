package jp.planit.seung.curriculum.validation.join;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jp.planit.seung.curriculum.dto.join.JoinRequest;
import jp.planit.seung.curriculum.service.JoinService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JoinValidator implements Validator {

  private final JoinService joinService;

  @Override
  public boolean supports(Class<?> clazz) {
    return JoinRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    JoinRequest request = (JoinRequest) target;

    // 生年月日チェック
    LocalDate today = LocalDate.now();
    LocalDate birthday = LocalDate.parse(request.getBirthday());
    if (joinService.isAfterDate(today, birthday)) {
      errors.rejectValue("birthday", null, "生年月日は本日よりも前の日付にしてください。");
    }
  }

}
