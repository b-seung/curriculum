package jp.planit.seung.curriculum.dto.join;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JoinPreRequest {

  @NotBlank(message = "メールアドレスは必須項目です。")
  @Email
  private String email;

  @NotBlank(message = "パスワードは必須項目です。")
  private String password;

  public JoinPreRequest encodePw(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }
}
