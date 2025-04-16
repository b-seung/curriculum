package jp.planit.seung.curriculum.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.dto.password.PasswordSelectDto;
import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.entity.User;

@Repository
@Mapper
public interface MemberMapper {
  /**
   * id検索
   * 
   * @param params
   * @return
   */
  MemberEntity findMember(Map<String, Object> params);

  /**
   * id、メールアドレス検索
   * 
   * @param params
   * @return
   */
  PasswordSelectDto resetInfo(Map<String, Object> params);

  /**
   * ログイン認証
   * 
   * @param params
   * @return
   */
  User login(Map<String, Object> params);

}
