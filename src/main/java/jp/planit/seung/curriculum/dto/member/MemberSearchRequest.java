package jp.planit.seung.curriculum.dto.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSearchRequest {
  private String id;
  private String name;
}
