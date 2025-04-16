package jp.planit.seung.curriculum.dto.join;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequest {
  @NotBlank(message = "idは必須項目です。")
  private String id;

  private String name;

  @NotBlank(message = "生年月日は必須項目です。")
  private String birthday;

  private String seibetsu;

  private String postcode;

  private String phoneNo;

  @NotBlank(message = "IDの重複チェックをしてください。")
  private String id_check;
}