package jp.planit.seung.curriculum.dto.join;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinIdCheckRequest {
  @NotBlank(message = "idは必須項目です。")
  @Min(message = "半角英数字3文字以上20文字以内で入力してください。", value = 3)
  @Max(message = "半角英数字3文字以上20文字以内で入力してください。", value = 20)
  private String id;
}
