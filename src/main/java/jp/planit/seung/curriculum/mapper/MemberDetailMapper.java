package jp.planit.seung.curriculum.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.entity.MemberDetailEntity;

@Repository
@Mapper
public interface MemberDetailMapper {
  /**
   * 
   * 
   * @param params
   * @return
   */
  MemberDetailEntity searchEmail(Map<String, Object> params);

}
