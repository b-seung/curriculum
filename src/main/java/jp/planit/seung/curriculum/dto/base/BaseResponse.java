package jp.planit.seung.curriculum.dto.base;

import lombok.Data;

@Data
public class BaseResponse {

  private int httpStatus;

  private String url;

}
