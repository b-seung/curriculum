package jp.planit.seung.curriculum.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {
  @Column
  private String delete_flg;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime create_date;

  @LastModifiedDate
  private LocalDateTime update_date;
}
