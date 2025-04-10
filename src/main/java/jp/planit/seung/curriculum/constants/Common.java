package jp.planit.seung.curriculum.constants;

import lombok.Getter;

@Getter
public enum Common {
  SEIBETSU("1"),;

  private final String value;

  Common(String value) {
    this.value = value;
  }
}