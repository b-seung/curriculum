package jp.planit.seung.curriculum.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jp.planit.seung.curriculum.dto.join.JoinPreRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "t_member")
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
  @Id
  private String id;

  @Column
  private String password;

  @Column
  private String member_id;

  @Builder
  public MemberEntity(String id, String password, String memberId) {
    this.id = id;
    this.password = password;
    this.member_id = memberId;
  }

  public MemberEntity encodePw(PasswordEncoder passwordEncoder) {
    this.password = passwordEncoder.encode(this.password);
    return this;
  }

}