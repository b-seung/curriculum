package jp.planit.seung.curriculum.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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

@Table(name = "token")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(columnDefinition = "json", nullable = false)
  private String token;

  @Column(columnDefinition = "datetime")
  private LocalDateTime create_date;

  @Column(columnDefinition = "datetime", nullable = false)
  private LocalDateTime limit_date;

  public TokenEntity(String token, LocalDateTime create, LocalDateTime limit) {
    this.token = token;
    this.create_date = create;
    this.limit_date = limit;
  }
}