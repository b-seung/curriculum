package jp.planit.seung.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.planit.seung.curriculum.entity.MemberDetailEntity;

public interface MemberDetailRepository extends JpaRepository<MemberDetailEntity, Integer> {

  MemberDetailEntity findByEmail(String email);
}