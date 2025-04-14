package jp.planit.seung.curriculum.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.planit.seung.curriculum.constants.Common;
import jp.planit.seung.curriculum.dto.JoinIdCheckResponse;
import jp.planit.seung.curriculum.dto.JoinRequest;
import jp.planit.seung.curriculum.entity.CommonEntity;
import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.mapper.CommonMapper;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService extends BaseService {
  private final CommonMapper commonMapper;
  private final MemberMapper memberMapper;

  public JoinIdCheckResponse checkId(String id) {
    JoinIdCheckResponse res = new JoinIdCheckResponse();

    MemberEntity entity = memberMapper.findMember(Map.of("id", id));

    if (entity == null) {
      res.setCnt("0");
    } else {
      res.setCnt("1");
    }

    return res;
  }

  @Transactional
  public void insert(JoinRequest request) {

  }

  public String getSeibetsu(String seibetsu) throws Exception {
    if (seibetsu == null) {
      return "答えたくない";
    }

    CommonEntity entity = commonMapper.get(Map.of("groupId", Common.SEIBETSU.getValue(), "id", seibetsu));

    if (entity == null) {
      throw new Exception("システムエラーが発生しました。システム管理者に連絡してください。");
    }

    return entity.getWord();
  }

  public boolean isAfterDate(LocalDate d1, LocalDate d2) {
    return d2.isAfter(d1);
  }
}
