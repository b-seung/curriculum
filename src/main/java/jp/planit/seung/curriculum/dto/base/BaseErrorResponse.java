package jp.planit.seung.curriculum.dto.base;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BaseErrorResponse {

  private HttpStatus error;

  private String message;
}
