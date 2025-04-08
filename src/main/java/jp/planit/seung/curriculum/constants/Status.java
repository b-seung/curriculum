package jp.planit.seung.curriculum.constants;

import lombok.Getter;

@Getter
public enum Status {
  ERROR("405"),
  OK("200"),;

  private final String value;

  Status(String value) {
    this.value = value;
  }

}
