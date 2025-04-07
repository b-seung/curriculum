package jp.planit.seung.curriculum.constants;

import lombok.Getter;

@Getter
public enum Flag {
  ON("1"),
  OFF("0"),;

  private final String value;

  Flag(String value) {
    this.value = value;
  }

}
