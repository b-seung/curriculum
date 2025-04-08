package jp.planit.seung.curriculum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.planit.seung.curriculum.entity.Member;
import jp.planit.seung.curriculum.entity.User;
import jp.planit.seung.curriculum.mapper.MemberMapper;

@Service
public class MemberService implements UserDetailsService {
  @Autowired
  private MemberMapper memberMapper;

  private BCryptPasswordEncoder encoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Member member = memberMapper.findMember(Map.of("id", username));
    if (member != null) {
      List<GrantedAuthority> authorities = new ArrayList();
      return new User(member.getId(), member.getPassword(), authorities);
    } else {

    }
    return null;
  }

  @Transactional
  public boolean join(String userId, String userPwd) {
    if (memberMapper.findMember(Map.of("id", userId)) != null) {
      return false;
    }

    Member newMember = new Member(userId, encoder.encode(userPwd), "1");
    // memberMapper.save(newMember);
    return true;
  }
}
