package jp.planit.seung.curriculum.service;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import jp.planit.seung.curriculum.entity.Member;

public class LoginService {
  // public void login(User account) {
  // UsernamePasswordAuthenticationToken token = new
  // UsernamePasswordAuthenticationToken( // 토큰 생성
  // account.getId(),
  // account.getPassword(),
  // List.of(new SimpleGrantedAuthority("ROLE_USER")));
  // // 로그인 처리
  // SecurityContextHolder.getContext().setAuthentication(token);
  // }
}
