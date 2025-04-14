package jp.planit.seung.curriculum.entity;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "t_member_detail")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetailEntity extends BaseEntity {
  @Id
  private String member_id;

  @Column
  private String email;

  @Column
  private String name;

  @Column
  private String gender;

  @Column
  private String birthday;

  @Column
  private String phone_no;

  @Column
  private String postcode;
}