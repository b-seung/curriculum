package jp.planit.seung.curriculum.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.planit.seung.curriculum.constants.Common;
import jp.planit.seung.curriculum.entity.CommonEntity;
import jp.planit.seung.curriculum.mapper.CommonMapper;

@Service
public class JoinService {
  @Autowired
  private CommonMapper commonMapper;

  public String getSeibetsu(String seibetsu) {
    if (seibetsu == null) {
      return "答えたくない";
    }

    CommonEntity entity = commonMapper.get(Map.of("groupId", Common.SEIBETSU.getValue(), "id", seibetsu));

    if (entity == null) {
      new Exception("システムエラーが発生しました。システム管理者に連絡してください。");
    }

    return entity.getWord();
  }
}
