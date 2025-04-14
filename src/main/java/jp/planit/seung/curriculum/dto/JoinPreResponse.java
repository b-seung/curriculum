package jp.planit.seung.curriculum.dto;

import jp.planit.seung.curriculum.dto.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JoinPreResponse extends BaseResponse {
  private String token;
}
