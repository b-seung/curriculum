package jp.planit.seung.curriculum.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseDataDTO {
  private String code;
  private String status;
  private String message;
}
