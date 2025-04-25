package jp.planit.seung.curriculum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.planit.seung.curriculum.entity.MemberDetailEntity;

public interface MemberDetailRepository extends JpaRepository<MemberDetailEntity, Integer> {

  Optional<MemberDetailEntity> findByEmail(String email);
}