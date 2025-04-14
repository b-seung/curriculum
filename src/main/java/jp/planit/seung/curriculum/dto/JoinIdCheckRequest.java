package jp.planit.seung.curriculum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinIdCheckRequest {
  @NotBlank(message = "idは必須項目です。")
  private String id;
}
