package jp.planit.seung.curriculum.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import jp.planit.seung.curriculum.entity.CommonEntity;

@Repository
@Mapper
public interface CommonMapper {
  CommonEntity get(Map<String, Object> param);
}
