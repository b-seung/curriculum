package jp.planit.seung.curriculum.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.entity.TokenEntity;

@Repository
@Mapper
public interface TokenMapper {

  void deleteToken(Map<String, Object> params);

  TokenEntity searchToken(Map<String, Object> params);

}
