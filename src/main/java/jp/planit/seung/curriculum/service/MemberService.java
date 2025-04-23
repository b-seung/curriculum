package jp.planit.seung.curriculum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jp.planit.seung.curriculum.dto.member.MemberSearchRequest;
import jp.planit.seung.curriculum.dto.member.MemberSearchResponse;
import jp.planit.seung.curriculum.dto.member.MemberInfo;
import jp.planit.seung.curriculum.mapper.MemberDetailMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService extends BaseService {

  private final MemberDetailMapper memberDetailMapper;

  public MemberSearchResponse search(MemberSearchRequest request) {
    MemberSearchResponse res = new MemberSearchResponse();

    List<MemberInfo> data = memberDetailMapper.search(Map.of("id", request.getId(), "name", request.getName()));

    if (data == null) {
      data = new ArrayList<>();
    }

    res.setData(data);

    return res;
  }
}
