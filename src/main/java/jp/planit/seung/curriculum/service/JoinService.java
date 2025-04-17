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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.common.util.StringUtils;
import jp.planit.seung.curriculum.constants.Common;
import jp.planit.seung.curriculum.dto.join.JoinIdCheckResponse;
import jp.planit.seung.curriculum.dto.join.JoinPreRequest;
import jp.planit.seung.curriculum.dto.join.JoinRequest;
import jp.planit.seung.curriculum.entity.CommonEntity;
import jp.planit.seung.curriculum.entity.MemberDetailEntity;
import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.entity.TokenEntity;
import jp.planit.seung.curriculum.mapper.CommonMapper;
import jp.planit.seung.curriculum.mapper.MemberDetailMapper;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import jp.planit.seung.curriculum.mapper.TokenMapper;
import jp.planit.seung.curriculum.repository.MemberDetailRepository;
import jp.planit.seung.curriculum.repository.MemberRepository;
import jp.planit.seung.curriculum.repository.TokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService extends BaseService {

  private final CommonMapper commonMapper;
  private final MemberMapper memberMapper;
  private final MemberDetailMapper memberDetailMapper;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final MemberRepository memberRepository;
  private final MemberDetailRepository memberDetailRepository;
  private final TokenRepository tokenRepository;
  private final TokenMapper tokenMapper;

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

    tokenMapper.deleteToken(Map.of("email", request.getEmail()));

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
  public void insert(JoinRequest request, TokenEntity tokenInfo) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode token = mapper.readTree(tokenInfo.getToken());

    MemberDetailEntity memberDetail = new MemberDetailEntity(
        token.get("email").asText(),
        request.getName(),
        Integer.parseInt(StringUtils.isEmpty(request.getSeibetsu()) ? "3" : request.getSeibetsu()),
        request.getBirthday(),
        request.getPhoneNo(),
        request.getPostcode());

    MemberDetailEntity mdEntity = memberDetailRepository.save(memberDetail);

    MemberEntity memberEntity = new MemberEntity(
        request.getId(),
        token.get("password").asText(),
        mdEntity.getMember_id());

    memberRepository.save(memberEntity);

    tokenRepository.deleteById(tokenInfo.getId());
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
