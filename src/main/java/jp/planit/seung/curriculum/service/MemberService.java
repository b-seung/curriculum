package jp.planit.seung.curriculum.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.planit.seung.curriculum.dto.member.MemberSearchRequest;
import jp.planit.seung.curriculum.dto.member.MemberSearchResponse;
import jp.planit.seung.curriculum.entity.MemberDetailEntity;
import jp.planit.seung.curriculum.entity.MemberEntity;
import jp.planit.seung.curriculum.dto.member.MemberEditRequest;
import jp.planit.seung.curriculum.dto.member.MemberInfo;
import jp.planit.seung.curriculum.mapper.MemberDetailMapper;
import jp.planit.seung.curriculum.repository.MemberDetailRepository;
import jp.planit.seung.curriculum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService extends BaseService {

  private final MemberDetailMapper memberDetailMapper;
  private final MemberRepository memberRepository;
  private final MemberDetailRepository memberDetailRepository;
  private final PasswordEncoder bCryptPasswordEncoder;
  private final ModelMapper modelMapper;

  public MemberSearchResponse search(MemberSearchRequest request) {
    MemberSearchResponse res = new MemberSearchResponse();

    List<MemberInfo> data = memberDetailMapper.search(Map.of("id", request.getId(), "name", request.getName()));

    if (data == null) {
      data = new ArrayList<>();
    }

    res.setData(data);

    return res;
  }

  @Transactional
  public void insert(MemberEditRequest request) {

    MemberDetailEntity memberDetailEntity = modelMapper.map(request, MemberDetailEntity.class);

    memberDetailMapper.insert(memberDetailEntity);

    MemberEntity memberEntity = modelMapper.map(request, MemberEntity.class);
    memberEntity.setMember_id(memberDetailEntity.getMember_id());
    memberEntity = memberEntity.encodePw(bCryptPasswordEncoder);

    memberRepository.save(memberEntity);
  }

  @Transactional
  public void update(MemberEditRequest request) {
    MemberDetailEntity memberDetailEntity = modelMapper.map(request, MemberDetailEntity.class);

    memberDetailRepository.save(memberDetailEntity);
  }

  @Transactional
  public void delete(String id, String mode) throws Exception {
    if (!mode.equals("delete")) {
      throw new Exception("");
    }

    Optional<MemberEntity> memberOptional = memberRepository.findById(id);
    MemberEntity member = null;

    if (memberOptional.isPresent()) {
      member = memberOptional.get();
    } else {
      throw new Exception("");
    }

    memberDetailRepository.deleteById(Integer.parseInt(member.getMember_id()));
    memberRepository.delete(member);
  }

  public boolean isAfterDate(LocalDate d1, LocalDate d2) {
    return d2.isAfter(d1);
  }

  public boolean checkEmail(String email) {
    Optional<MemberDetailEntity> entity = memberDetailRepository.findByEmail(email);

    return entity.isPresent();
  }

  public boolean checkId(String id) {
    Optional<MemberEntity> entity = memberRepository.findById(id);

    return entity.isPresent();
  }
}
