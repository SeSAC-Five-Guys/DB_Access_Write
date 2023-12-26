package com.sesac.db_access_write.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberSignUpInfo {
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[A-Za-z]+$", message = "잘못된 이메일 형식")
	@Size(max = 100, message = "이메일 길이 초과")
	@NotBlank(message = "이메일 공백 포함 불가")
	private String email;

	@Pattern(regexp = "^01[0-9]{9}$", message = "잘못된 핸드폰 번호 형식")
	@NotBlank(message = "핸드폰 번호 공백 포함 불가")
	private String phoneNumber;

	@Pattern(regexp = "^[^!@#_]{1,12}$", message = "잘못된 닉네임 형식")
	@NotBlank(message = "닉네임 공백 포함 불가")
	private String nickname;

	@Pattern(regexp = "^[^!@#_]{1,12}$", message = "잘못된 비밀번호 형식")
	@NotBlank(message = "비밀번호 공백 포함 불가")
	private String password;
}
