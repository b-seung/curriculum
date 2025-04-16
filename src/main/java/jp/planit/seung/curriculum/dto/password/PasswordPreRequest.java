package jp.planit.seung.curriculum.dto.password;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordPreRequest {

  private String id;

  @NotBlank(message = "メールアドレスは必須項目です。")
  private String email;

  @NotBlank(message = "生年月日は必須項目です。")
  private String birthday;

  private String searchKbn;
}
