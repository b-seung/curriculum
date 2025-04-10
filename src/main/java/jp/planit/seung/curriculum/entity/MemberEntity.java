package jp.planit.seung.curriculum.entity;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "t_member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {
  @Id
  private String id;

  @Column
  private String password;

  @Column
  private String member_id;

  @Builder
  public MemberEntity(String id, String pw, String memberId) {
    this.id = id;
    this.password = pw;
    this.member_id = memberId;
  }
}