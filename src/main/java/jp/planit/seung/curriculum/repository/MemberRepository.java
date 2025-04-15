package jp.planit.seung.curriculum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.planit.seung.curriculum.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

}