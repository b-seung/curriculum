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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetailEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String member_id;

  @Column
  private String email;

  @Column
  private String name;

  @Column
  private int gender;

  @Column
  private String birthday;

  @Column
  private String phone_no;

  @Column
  private String postcode;

  @Builder
  public MemberDetailEntity(String email, String name, int gender, String birthday, String phoneNo,
      String postcode) {
    this.email = email;
    this.name = name;
    this.gender = gender;
    this.birthday = birthday;
    this.phone_no = phoneNo;
    this.postcode = postcode;
  }
}