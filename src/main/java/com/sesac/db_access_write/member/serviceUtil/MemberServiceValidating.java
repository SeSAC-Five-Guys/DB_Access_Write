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

	public boolean isChangeInfoDuplicated(String currentData, String changeData, boolean existData){
		boolean result = false;

		// 현재 데이터와 변경할 데이터가  일치하지 않고
		if(!currentData.equals(changeData)){
			// 변경할 데이터가 중복된다면 데이터 중복으로 변경 불가
			if(existData){
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
