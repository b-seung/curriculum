package jp.planit.seung.curriculum.dto.member;

import jp.planit.seung.curriculum.dto.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberSearchResponse extends BaseResponse {

  List<MemberInfo> data;

}
