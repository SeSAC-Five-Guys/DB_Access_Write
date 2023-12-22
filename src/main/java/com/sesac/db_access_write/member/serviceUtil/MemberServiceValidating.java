package com.sesac.db_access_write.member.serviceUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

// 중복 확인 판별 메서드 모음
@Log4j2
@Component
public class MemberServiceValidating {
	// 회원 가입 시, 모든 중복 확인 판별식.
	public boolean isDuplicatedValCnt(long valueCnt){
		return valueCnt != 0L;
	}

	public boolean isChangeNickNameDuplicated(String currentNickname, String changeNickname, boolean existNickname){
		boolean result = false;

		// 현재 닉네임과 변경할 닉네임이 일치하지 않고
		if(!currentNickname.equals(changeNickname)){
			// 변경할 닉네임이 중복된다면 닉네임 중복으로 변경 불가
			if(existNickname){
				result = true;
			}
		}
		return result;
	}

	public String encodingPwd(String rawPassword){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(rawPassword);
	}

	public boolean isMatch(String rawPassword, String encodedPassword){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}
}
