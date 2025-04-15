package jp.planit.seung.curriculum.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jp.planit.seung.curriculum.handler.LoginFailHandler;
import jp.planit.seung.curriculum.handler.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final LoginSuccessHandler successHandler;
  private final LoginFailHandler failHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(c -> c.disable())
        // .httpBasic(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**").permitAll()
            .requestMatchers("/join", "/join/**", "/login", "/password/**", "/error").permitAll()
            .anyRequest().authenticated())
        .formLogin((formLogin) -> formLogin
            .loginPage("/login")
            .loginProcessingUrl("/loging")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(successHandler)
            .failureHandler(failHandler)
            .permitAll())
        .logout((logoutConfig) -> logoutConfig
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true));

    return http.build();
  }
}
