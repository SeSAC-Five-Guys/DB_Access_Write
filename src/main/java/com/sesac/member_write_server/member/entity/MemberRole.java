package com.sesac.member_write_server.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
	USER("ROLE_USER1", "일반"),
	ADMIN("ROLE_ADMIN", "관리자");

	private final String key;
	private final String value;
}
