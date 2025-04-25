package jp.planit.seung.curriculum.dto.member;

import groovy.transform.EqualsAndHashCode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberEditRequest {
  private String mode;

  @NotBlank(message = "IDは必須項目です。")
  private String id;

  private String password;

  private String member_id;

  @NotBlank(message = "メールアドレスは必須項目です。")
  private String email;

  private String name;

  private String gender;

  private String gender_name;

  @NotBlank(message = "生年月日は必須項目です。")
  private String birthday;

  private String phone_no;

  private String postcode;

  private String delete_flg;

  private String create_date;

  private String update_date;
}
