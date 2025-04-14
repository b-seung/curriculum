package jp.planit.seung.curriculum.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  public CustomException(String message) {
    super(message);
  }
}