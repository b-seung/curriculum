package jp.planit.seung.curriculum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;

@Table(name = "t_member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonEntity extends BaseEntity {
  @Id
  private String group_id;

  @Id
  private String id;

  @Column
  private String word;

  @Builder
  public CommonEntity(String groupId, String id, String word) {
    this.group_id = groupId;
    this.id = id;
    this.word = word;
  }
}
