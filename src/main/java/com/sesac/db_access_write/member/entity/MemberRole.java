package com.sesac.db_access_write.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	USER_1("ROLE_USER1", "일반"),
	USER_2("ROLE_USER2", "우대"),

	// USER_3(), ...
	ADMIN("ROLE_ADMIN", "관리자");

	private final String key;
	private final String value;
}
