package jp.planit.seung.curriculum.entity;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "m_menu")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuEntity extends BaseEntity {
  @Id
  private String menu_id;

  @Column
  private String menu_name;

  @Column
  private String url;

  @Builder
  public MenuEntity(String menu_id, String menu_name, String url) {
    this.menu_id = menu_id;
    this.menu_name = menu_name;
    this.url = url;
  }
}