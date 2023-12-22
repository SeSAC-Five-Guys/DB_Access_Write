package com.sesac.db_access_write.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sesac.db_access_write.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByEmail(String email);
	long countByEmail(String email);
	long countByNickname(String nickname);
	long countByPhoneNumber(String name);
}
