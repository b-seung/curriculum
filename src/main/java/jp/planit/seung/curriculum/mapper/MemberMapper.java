package jp.planit.seung.curriculum.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.entity.User;

@Repository
@Mapper
public interface MemberMapper {
  /**
   * idに
   * 
   * @param params
   * @return
   */
  MemberEntity findMember(Map<String, Object> params);

  User login(Map<String, Object> params);

}
