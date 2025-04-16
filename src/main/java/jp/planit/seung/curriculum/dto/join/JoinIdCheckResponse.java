package jp.planit.seung.curriculum.dto.join;

import jp.planit.seung.curriculum.dto.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JoinIdCheckResponse extends BaseResponse {
  private String cnt;
}
