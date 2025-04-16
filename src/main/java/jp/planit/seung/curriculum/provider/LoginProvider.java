package jp.planit.seung.curriculum.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jp.planit.seung.curriculum.entity.User;
import jp.planit.seung.curriculum.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginProvider implements AuthenticationProvider {

  private final MemberMapper memberMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Object details = authentication.getDetails();

    String userId = authentication.getName();
    String userPw = (String) authentication.getCredentials();

    Object resultObj = null;
    String resultPw = "";

    User userInfo = memberMapper.login(Map.of("id", userId));

    if (userInfo == null) {
      throw new UsernameNotFoundException("");
    } else {
      resultPw = userInfo.getPassword();
      resultObj = userInfo;
    }

    if (!passwordEncoder.matches(userPw, resultPw)) {
      throw new BadCredentialsException("");
    }

    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    roles.add(new SimpleGrantedAuthority("MEMBER"));

    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, userPw, roles);
    authToken.setDetails(resultObj);

    return authToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

}
