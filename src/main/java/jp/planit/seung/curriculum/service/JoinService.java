package jp.planit.seung.curriculum.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.planit.seung.curriculum.constants.Common;
import jp.planit.seung.curriculum.dto.JoinIdCheckResponse;
import jp.planit.seung.curriculum.dto.JoinPreRequest;
import jp.planit.seung.curriculum.dto.JoinRequest;
import jp.planit.seung.curriculum.entity.CommonEntity;
import jp.planit.seung.curriculum.entity.MemberDetailEntity;
import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.entity.TokenEntity;
import jp.planit.seung.curriculum.mapper.CommonMapper;
import jp.planit.seung.curriculum.mapper.MemberDetailMapper;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import jp.planit.seung.curriculum.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService extends BaseService {

  private final CommonMapper commonMapper;
  private final MemberMapper memberMapper;
  private final MemberDetailMapper memberDetailMapper;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final TokenRepository tokenRepository;

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
  public String getToken(JoinPreRequest request) throws JSONException {
    UUID uuid4 = UUID.randomUUID();

    request = request.encodePw(bCryptPasswordEncoder);

    JSONObject json = new JSONObject();
    json.put("token", uuid4);
    json.put("email", request.getEmail());
    json.put("password", request.getPassword());

    LocalDateTime create = LocalDateTime.now();
    LocalDateTime limit = create.plusMinutes(15);
    TokenEntity entity = new TokenEntity(json.toString(), create, limit);
    tokenRepository.save(entity);

    return uuid4.toString();
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

  public boolean existEmail(String email) {
    MemberDetailEntity entity = memberDetailMapper.searchEmail(Map.of("email", email));

    if (entity == null) {
      return false;
    }

    return true;
  }

  public boolean isAfterDate(LocalDate d1, LocalDate d2) {
    return d2.isAfter(d1);
  }
}
