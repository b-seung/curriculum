package jp.planit.seung.curriculum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinRequestDto {
  private String id;
  private String name;
  private String birthday;
  private String seibetsu;
  private String postcode;
  private String phoneNo;
}