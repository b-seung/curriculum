package jp.planit.seung.curriculum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.planit.seung.curriculum.service.LoginService;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
  // private LoginService loginService;

  // @Bean
  // public PasswordEncoder passwordEncoder() {
  // return new BCryptPasswordEncoder();
  // }

  // @Override
  // public void configure(WebSecurity web) throws Exception {
  // web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
  // }

  // @Bean
  // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
  // Exception {
  // return http.formLogin()
  // .loginPage("/login")
  // .defaultSuccessUrl("/user/login/result")
  // .permitAll()
  // .and() // 로그아웃 설정
  // .logout()
  // .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
  // .logoutSuccessUrl("/user/logout/result")
  // .invalidateHttpSession(true)
  // .and()
  // // 403 예외처리 핸들링
  // .exceptionHandling().accessDeniedPage("/user/denied").build();
  // }

  // @Override
  // public void configure(AuthenticationManagerBuilder auth) throws Exception {
  // auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
  // }
}
