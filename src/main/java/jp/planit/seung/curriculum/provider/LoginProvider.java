package jp.planit.seung.curriculum.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jp.planit.seung.curriculum.entity.Member;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginProvider implements AuthenticationProvider {

  @Autowired
  private MemberMapper memberMapper;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Object details = authentication.getDetails();

    String userId = authentication.getName();
    String userPw = (String) authentication.getCredentials();

    String resultUserPw = "";
    Object resultObj = null;

    Member userInfo = memberMapper.findMember(Map.of("id", userId));

    if (userInfo == null) {
      throw new UsernameNotFoundException("");
    } else {
      resultUserPw = userInfo.getPassword();
      resultObj = userInfo;
    }

    if (!userPw.equals(resultUserPw)) {
      throw new BadCredentialsException("");
    }

    // 권한 리스트
    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    roles.add(new SimpleGrantedAuthority("MEMBER"));

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, userPw, roles);
    authToken.setDetails(resultObj);

    return authToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
