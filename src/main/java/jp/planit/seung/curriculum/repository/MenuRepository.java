package jp.planit.seung.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.planit.seung.curriculum.entity.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {

}
