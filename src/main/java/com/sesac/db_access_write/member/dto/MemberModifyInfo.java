package com.sesac.db_access_write.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberModifyInfo {
	@Pattern(regexp = "^01[0-9]{9}$", message = "잘못된 핸드폰 번호 형식")
	@NotBlank(message = "핸드폰 번호 공백 포함 불가")
	private String phoneNumber;

	@Pattern(regexp = "^[^!@#_]{5,12}$", message = "잘못된 닉네임 형식")
	@NotBlank(message = "닉네임 공백 포함 불가")
	private String nickname;
}
