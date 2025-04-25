package jp.planit.seung.curriculum.dto.member;

import groovy.transform.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberNextRequest {
  private String id;
  private String member_id;
  private String name;
  private String email;
  private String gender;
  private String gender_name;
  private String birthday;
  private String phone_no;
  private String postcode;
  private String delete_flg;
  private String create_date;
  private String update_date;
}
