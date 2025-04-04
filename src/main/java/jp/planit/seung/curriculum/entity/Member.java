package jp.planit.seung.curriculum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "t_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
  @Id
  private String id;

  private String password;

  private String member_id;
}
