package jp.planit.seung.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.planit.seung.curriculum.entity.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

}