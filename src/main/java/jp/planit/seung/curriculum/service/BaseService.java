package jp.planit.seung.curriculum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class BaseService {

  public String getValidErrorMsg(Errors errors) {
    List<String> msgs = new ArrayList();

    for (FieldError error : errors.getFieldErrors()) {
      msgs.add(error.getDefaultMessage());
    }

    return String.join("\n", msgs);
  }
}
