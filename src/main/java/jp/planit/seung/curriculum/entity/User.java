package jp.planit.seung.curriculum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  private String id;
  private String password;
  private String name;
  private String email;

}
