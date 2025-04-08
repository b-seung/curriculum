package jp.planit.seung.curriculum.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jp.planit.seung.curriculum.constants.Flag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "t_member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
  @Id
  private String id;

  @Column
  private String password;

  @Column
  private String member_id;

  @Builder
  public Member(String id, String pw, String memberId) {
    this.id = id;
    this.password = pw;
    this.member_id = memberId;
  }
}