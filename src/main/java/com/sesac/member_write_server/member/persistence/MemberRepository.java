package com.sesac.member_write_server.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sesac.member_write_server.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByEmailAndDeletedAtIsNull(String email);

	long countByEmail(String email);
	long countByNickname(String nickname);
	long countByPhoneNumber(String name);
}
