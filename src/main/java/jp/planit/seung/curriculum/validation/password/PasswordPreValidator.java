package jp.planit.seung.curriculum.validation.password;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.micrometer.common.util.StringUtils;
import jp.planit.seung.curriculum.dto.password.PasswordPreRequest;
import jp.planit.seung.curriculum.service.PasswordService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordPreValidator implements Validator {

  private final PasswordService service;

  @Override
  public boolean supports(Class<?> clazz) {
    return PasswordPreRequest.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    PasswordPreRequest request = (PasswordPreRequest) target;

    if (request.getSearchKbn().equals("1")) {
      if (StringUtils.isEmpty(request.getId())) {
        errors.rejectValue("id", null, "IDは必須項目です。");
      }
    }
  }
}
