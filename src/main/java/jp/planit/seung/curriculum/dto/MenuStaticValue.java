package jp.planit.seung.curriculum.dto;

import java.util.ArrayList;
import java.util.List;

import jp.planit.seung.curriculum.entity.MenuEntity;
import lombok.Getter;

@Getter
public class MenuStaticValue {
  public static List<MenuEntity> menuList = new ArrayList<>();
}
