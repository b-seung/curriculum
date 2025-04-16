package jp.planit.seung.curriculum.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import jp.planit.seung.curriculum.dto.password.PasswordPreRequest;
import jp.planit.seung.curriculum.dto.password.PasswordSelectDto;
import jp.planit.seung.curriculum.entity.TokenEntity;
import jp.planit.seung.curriculum.exception.CustomException;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import jp.planit.seung.curriculum.mapper.TokenMapper;
import jp.planit.seung.curriculum.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordService extends BaseService {

  private final MemberMapper memberMapper;
  private final TokenMapper tokenMapper;
  private final TokenRepository tokenRepository;

  public String getTokenOrInfo(PasswordPreRequest request) throws JSONException {
    PasswordSelectDto dto = memberMapper.resetInfo(
        Map.of(
            "email", request.getEmail(),
            "birthday", request.getBirthday(),
            "id", StringUtils.isEmpty(request.getId()) ? "" : request.getId()));

    if (dto == null) {
      throw new CustomException("入力された情報に一致する会員は存在しません。");
    }

    if (request.getSearchKbn().equals("0")) {

      String id = dto.getId();
      int length = id.length();
      int half = Math.min(length / 2, 7);

      return id.substring(0, half) + "*".repeat(length - half);

    } else {
      tokenMapper.deleteToken(Map.of("email", request.getEmail()));

      UUID uuid4 = UUID.randomUUID();

      JSONObject json = new JSONObject();
      json.put("token", uuid4);
      json.put("email", request.getEmail());

      LocalDateTime create = LocalDateTime.now();
      LocalDateTime limit = create.plusMinutes(15);
      TokenEntity entity = new TokenEntity(json.toString(), create, limit);
      tokenRepository.save(entity);

      return uuid4.toString();
    }
  }
}
