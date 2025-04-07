package jp.planit.seung.curriculum.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.entity.Member;

@Repository
@Mapper
public interface MemberMapper {
  Member findMember(Map<String, Object> params);
}
